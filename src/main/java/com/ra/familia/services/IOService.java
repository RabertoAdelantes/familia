package com.ra.familia.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;

public class IOService {

	private static final Logger LOG = LoggerFactory
			.getLogger(IOService.class);
	
	private static final String USER_UPLOAD_FOLDER = "user_upload_folder";
	private static final String FILE_MAX_SIZE = "file.size.max";
	private static final String USER_UPLOAD_DEFAUL_FOLDER = "Z:\\";
	
	private static Properties properties = PropertiesManager
			.getCommonProperties();
	
	public String getFileName(final FileItem item) {
		String directory = properties.getProperty(USER_UPLOAD_FOLDER,
				USER_UPLOAD_DEFAUL_FOLDER);
		String name = directory + File.separator;
		if (isFileExists(name + item.getName())) {
			String[] fileAttr = item.getName().split("\\.");
			String fName = fileAttr[0];
			String fExt = fileAttr[1];
			name += fName + "_" + UUID.randomUUID().toString() + "." + fExt;
		}
		return name;
	}

	public boolean isFileExists(final String fname) {
		return new File(fname).exists();
	}
	
	public void storageFile(PersonBean bean) {
		String max_size = properties.getProperty(FILE_MAX_SIZE, "1048576");
		FileItem item = (FileItem) bean.getFilePath();
		String name = getFileName(item);
		try {
			item.write(new File(name));
			bean.setFilePath(name);
			
			if (item.getSize() < Long.valueOf(max_size)) {
				FileInputStream in = new FileInputStream(name);
				byte[] bytes = IOUtils.toByteArray(in);
				bean.setDbFile(bytes);
			} 
		} catch (Exception ex) {
			LOG.error(String.format("Error occured on save '%s'",
					ex.getLocalizedMessage()));
		}
	}
}
