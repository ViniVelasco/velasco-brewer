package com.velasco.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.velasco.brewer.dto.PhotoDTO;

public class PhotoStorageRunnable implements Runnable {

	private MultipartFile[] files; 
	private DeferredResult<PhotoDTO> result;
	
	public PhotoStorageRunnable(MultipartFile[] files, DeferredResult<PhotoDTO> result) {
		this.files = files;
		this.result = result;
	}

	@Override
	public void run() {
		String photoName = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		result.setResult(new PhotoDTO(photoName, contentType));
	}

}
