package com.velasco.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.velasco.brewer.storage.PhotoStorage;

public class PhotoStorageLocal implements PhotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(PhotoStorageLocal.class);
	
	private Path local;
	private Path temporaryLocal;
	
	public PhotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOMEPATH"), ".brewerphotos"));
	}
	
	public PhotoStorageLocal(Path path) {
		this.local = path;
		createFolders();
	}
	

	@Override
	public String temporarySave(MultipartFile[] files) {
		String newName = null;
		if(files != null && files.length > 0) {
			MultipartFile file = files[0];
			newName = renameFile(file.getOriginalFilename());
			try {
				file.transferTo(new File(this.temporaryLocal.toAbsolutePath().toString() + getDefault().getSeparator() + newName));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}
			
			return newName;
			
		}
		
		return newName;
		
	}
	
	@Override
	public byte[] temporaryPhotoRecover(String name) {
		try {
			return Files.readAllBytes(this.temporaryLocal.resolve(this.temporaryLocal.resolve(name)));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária");
		}
	}
	
	private void createFolders() {
		try {
			Files.createDirectories(this.local);
			this.temporaryLocal= getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.temporaryLocal);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos");
				logger.debug("Pasta default " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária " + this.temporaryLocal.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
		
	}
	
	private String renameFile(String fileNameOriginal) {
		String newName = UUID.randomUUID().toString() + "_" + fileNameOriginal;
		
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original %s, novo nome do arquivo: %s", fileNameOriginal, newName));
		}
		
		return newName;
	}
	
}
