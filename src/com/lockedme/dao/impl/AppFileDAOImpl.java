package com.lockedme.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lockedme.dao.AppFileDAO;
import com.lockedme.exception.AppFileException;
import com.lockedme.model.AppFile;

public class AppFileDAOImpl implements AppFileDAO{

	private static Map<Integer, AppFile> appFileMap = new HashMap<>();
	private static int fileCount=0;
	
	@Override
	public AppFile addAppFile(AppFile appfile) throws AppFileException {
		appfile.setFileId(++fileCount);
		appFileMap.put(appfile.getFileId(), appfile);
		return appfile;
	}

	@Override
	public void deleteAppFileById(int fileId) throws AppFileException {
		if(appFileMap.containsKey(fileId)) {
			appFileMap.remove(fileId);
		}else {
			throw new AppFileException("Entered id "+fileId+" doesnt exist");
		}
	}

	@Override
	public void deleteAppFileByName(String fileName) throws AppFileException {
		List<AppFile> getAppFileByName = appFileMap.values().stream().filter(s->s.getFileName().equals(fileName)).collect(Collectors.toList());
		
		if(getAppFileByName.size()!=0) {
			appFileMap.remove(getAppFileByName.get(0).getFileId());
		}else {
			throw new AppFileException("Entered name "+fileName+" doesnt exist");
		}
	}

	@Override
	public AppFile getAppFileByName(String fileName) throws AppFileException {
		List<AppFile> getAppFileByName = appFileMap.values().stream().filter(s->s.getFileName().equals(fileName)).collect(Collectors.toList());
		
		if(getAppFileByName.size()!=0) {
			return getAppFileByName.get(0);
		}else {
			throw new AppFileException("File with name = "+fileName+" does not exist in the Application");
		}
	}

	@Override
	public List<AppFile> getAllAppFiles() throws AppFileException {
		if(appFileMap.size()==0) {
			throw new AppFileException("No Files in App.");
		}
		List<AppFile> listOFiles = new ArrayList<>(appFileMap.values());
		Collections.sort(listOFiles, (f1,f2)->{
			return f1.getFileName().compareTo(f2.getFileName());
		});
		return listOFiles;
	}

	@Override
	public List<AppFile> getAppFilesByType(String fileType) throws AppFileException {
		List<AppFile> getAppFileByType = appFileMap.values().stream().filter(s->s.getFileType().equals(fileType)).collect(Collectors.toList());
		if(getAppFileByType.size()!=0) {
			return getAppFileByType;
		}else {
			throw new AppFileException("Entered name "+fileType+" doesnt exist");
		}
	}

	@Override
	public List<AppFile> getAppFilesByDateAdded(String dateAdded) throws AppFileException {
		List<AppFile> getAppFileByDate = appFileMap.values().stream().filter(s->s.getDateAdded().equals(dateAdded)).collect(Collectors.toList());
		if(getAppFileByDate.size()!=0) {
			return getAppFileByDate;
		}else {
			throw new AppFileException("Entered name "+dateAdded+" doesnt exist");
		}
	}
	
	public void storeMapData() {
		try
        {
               FileOutputStream fos =
                  new FileOutputStream("FileData.ser");
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(appFileMap);
               oos.close();
               fos.close();
        }catch(IOException ioe)
         {
        	return;
         }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getStoredMap() throws AppFileException {
	      try
	      {
	         FileInputStream fis = new FileInputStream("FileData.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         appFileMap = (Map<Integer, AppFile>) ois.readObject();
	         Set<Integer> counters = appFileMap.keySet();
	         fileCount=Collections.max(counters);
	         ois.close();
	         fis.close();
	      }catch(IOException ioe)
	      {
			return;
	      }catch(ClassNotFoundException c)
	      {
	         return;
	      }
		
	}

}
