package com.lockedme.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AppFile implements Serializable{
	private int fileId;
	private String fileName;
	private String fileType;
	private String dateAdded;
	
	public AppFile() {
		
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return "AppFile [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", dateAdded="
				+ dateAdded + "] ";
	}
	
	
}
