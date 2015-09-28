package com.ra.familia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;

public class IOService {

	private static final Logger LOG = LoggerFactory
			.getLogger(IOService.class);
	
	private static final String USER_UPLOAD_FOLDER = "user_upload_folder";
	private static final String USER_UPLOAD_DEFAUL_FOLDER = "Z:\\";
	String MAX_SIZE = properties.getProperty("file.size.max", "1048576");
	private ApplicationCashe imgCashe = ApplicationCashe.getInsatnce();

	private static Properties properties = PropertiesManager
			.getCommonProperties();
	
	public String getFileName(final FileItem item) {
		String directory = properties.getProperty(USER_UPLOAD_FOLDER,
				USER_UPLOAD_DEFAUL_FOLDER);
		String name = directory + File.separator;
		name += UUID.randomUUID().toString() + "." + item.getContentType().split("/")[1];
		return name;
	}

	public boolean isFileExists(final String fname) {
		return new File(fname).exists();
	}
	
	public void storageFile(PersonBean bean) {
		DiskFileItem item = (DiskFileItem) bean.getFilePath();
		FileInputStream in = null;
		try {
				in = new FileInputStream(item.getStoreLocation());
				byte[] bytes = IOUtils.toByteArray(in);
				bean.setDbFile(bytes);
			//imgCashe.add(bean.getID()+IMG_SUFFIX,getMediaFromFs(name));
		} catch (Exception ex) {
			LOG.error(String.format("Error occured on save '%s'",
					ex.getLocalizedMessage()));
		}
		finally
		{
			IOUtils.closeQuietly(in);			
		}
	}
	
	public byte[] getMediaFromFs(String filePath) {
		byte[] returnValue = null;
		File file = new File(filePath);
		if (file.exists()) {
			try {
				returnValue = Files.readAllBytes(file.toPath());
			} catch (IOException ioxe) {
				LOG.error(">>> getMedia : " + ioxe.getMessage());
			}
		}
		return returnValue;
	}
	
	public ApplicationCashe getCashe()
	{
		return imgCashe;
	}
}
