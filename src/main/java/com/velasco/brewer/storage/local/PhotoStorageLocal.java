package com.velasco.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.velasco.brewer.storage.PhotoStorage;

public class PhotoStorageLocal implements PhotoStorage{

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

	@Override
	public void temporarySave(MultipartFile[] files) {
		System.out.print("Salvando");
		
	}
}
