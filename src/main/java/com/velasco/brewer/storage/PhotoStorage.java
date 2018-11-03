package com.velasco.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	public String temporarySave(MultipartFile[] files);

	public byte[] temporaryPhotoRecover(String name);

	public void save(String photo);

	public byte[] recover(String photo);
}
