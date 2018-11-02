package com.velasco.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.velasco.brewer.dto.PhotoDTO;
import com.velasco.brewer.storage.PhotoStorage;
import com.velasco.brewer.storage.PhotoStorageRunnable;

@RestController
@RequestMapping("/photos")
public class PhotosController {

	@Autowired
	private PhotoStorage photoStorage;
	
	@PostMapping
	public DeferredResult<PhotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<PhotoDTO> result = new DeferredResult<>();
		
		Thread thread = new Thread(new PhotoStorageRunnable(files, result, photoStorage));
		thread.start();
		
		return result;
	}
	
	@GetMapping("/temp/{name:.*}")
	public byte[] temporaryPhotoRecover(@PathVariable("name") String name) {
		return photoStorage.temporaryPhotoRecover(name);
	}
}
