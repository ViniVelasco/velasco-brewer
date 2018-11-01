package com.velasco.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.velasco.brewer.dto.PhotoDTO;

public class PhotoStorageRunnable implements Runnable {

	private MultipartFile[] files; 
	private DeferredResult<PhotoDTO> result;
	private PhotoStorage photoStorage;
	
	public PhotoStorageRunnable(MultipartFile[] files, DeferredResult<PhotoDTO> result, PhotoStorage photoStorage) {
		this.files = files;
		this.result = result;
		this.photoStorage = photoStorage;
	}

	@Override
	public void run() {
		String photoName = this.photoStorage.temporarySave(files);
		String contentType = files[0].getContentType();

		result.setResult(new PhotoDTO(photoName, contentType));
	}

}
