package com.lockedme.service;

import java.util.List;

import com.lockedme.exception.AppFileException;
import com.lockedme.model.AppFile;

public interface AppFileService {
	
	public AppFile addAppFile(AppFile appfile) throws AppFileException;
	public void deleteAppFileById(int fileId) throws AppFileException;
	public void deleteAppFileByName(String fileName) throws AppFileException;
	public AppFile getAppFileByName(String fileName) throws AppFileException;
	
	public List<AppFile> getAllAppFiles() throws AppFileException;
	public List<AppFile> getAppFilesByType(String fileType) throws AppFileException;
	public List<AppFile> getAppFilesByDateAdded(String dateAdded) throws AppFileException;
	
	public void storeData() throws AppFileException;
	public void getStoredData() throws AppFileException;
}
