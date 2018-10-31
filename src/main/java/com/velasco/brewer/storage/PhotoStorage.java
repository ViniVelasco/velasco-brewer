package com.velasco.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	public void temporarySave(MultipartFile[] files);
}
