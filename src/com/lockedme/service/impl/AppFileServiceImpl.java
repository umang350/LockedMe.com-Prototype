package com.lockedme.service.impl;

import java.util.List;

import com.lockedme.dao.AppFileDAO;
import com.lockedme.dao.impl.AppFileDAOImpl;
import com.lockedme.exception.AppFileException;
import com.lockedme.model.AppFile;
import com.lockedme.service.AppFileService;

public class AppFileServiceImpl implements AppFileService{

	private AppFileDAO dao = new AppFileDAOImpl();
	
	@Override
	public AppFile addAppFile(AppFile appfile) throws AppFileException {
		if(!isValidName(appfile.getFileName())) {
			throw new AppFileException("Invalid Name "+appfile.getFileName());
		}else if(!isValidType(appfile.getFileType())) {
			throw new AppFileException("Invalid File Type "+appfile.getFileType());	
		}
		return dao.addAppFile(appfile);
	}

	private boolean isValidId(int id) {
		boolean b=true;
		if(id<0 && id>100000000) {
			b=false;
		}
		return b;
	}
	
	private boolean isValidName(String name) {
		boolean b=false;
		if(name.trim().matches("[a-zA-Z0-9 .]{3,20}")) {
			b=true;
		}
		return b;
	}
	
	private boolean isValidType(String type) {
		boolean b=false;
		if(type.equals("Text") || type.equals("Image") || type.equals("Video") ) {
			b=true;
		}
		return b;
	}
	
	@Override
	public void deleteAppFileById(int fileId) throws AppFileException {
		if(!isValidId(fileId)) {
			throw new AppFileException("Invalid Id "+fileId);
		}else {
			dao.deleteAppFileById(fileId);
		}
		
	}

	@Override
	public void deleteAppFileByName(String fileName) throws AppFileException {
		// TODO Auto-generated method stub
		dao.deleteAppFileByName(fileName);
		
	}

	@Override
	public AppFile getAppFileByName(String fileName) throws AppFileException {
		return dao.getAppFileByName(fileName);
	}

	@Override
	public List<AppFile> getAllAppFiles() throws AppFileException {
		return dao.getAllAppFiles();
	}

	@Override
	public List<AppFile> getAppFilesByType(String fileType) throws AppFileException {
		return dao.getAppFilesByType(fileType);
	}

	@Override
	public List<AppFile> getAppFilesByDateAdded(String dateAdded) throws AppFileException {
		return dao.getAppFilesByDateAdded(dateAdded);
	}
	
	@Override
	public void storeData() {
		try {
			dao.storeMapData();
		} catch (AppFileException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void getStoredData() throws AppFileException {
		dao.getStoredMap();
		
	}

}
