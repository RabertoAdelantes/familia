package com.ra.familia.services;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.SuperBean;

public class ApplicationCashe {
	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationCashe.class);

	private static ApplicationCashe appCashe = new ApplicationCashe();
	private static Map<String, byte[]> imgCashe;

	private ApplicationCashe() {
		if (imgCashe == null) {
			imgCashe = new HashMap<>();
		}
	}

	public static ApplicationCashe getInsatnce() {
		return appCashe;
	}

	public Object getObject(String key) {
		return convertFromBytes(imgCashe.get(key));
	}

	public byte[] get(String key) {
		return imgCashe.get(key);
	}

	public void add(String key, byte[] value) {
		imgCashe.put(key, value);
	}

	public void add(SuperBean bean) {
		if (bean != null) {
			try {
				imgCashe.put(bean.getID(), convertToBytes(bean));
			} catch (IOException ex) {
				LOG.error(String.format(
						"Convert object to bytes errror: {'%s'}", ex));
			}
		}
	}

	void clear() {
		imgCashe.clear();
	}

	private byte[] convertToBytes(SuperBean object) throws IOException {
		return object==null?null:SerializationUtils.serialize(object);
	}

	private Object convertFromBytes(byte[] bytes) {
		return bytes==null?null:SerializationUtils.deserialize(bytes);

	}
}
