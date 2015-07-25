package com.ra.familia.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.SuperBean;

public class AppliactionCashe {
	private static final Logger LOG = LoggerFactory
			.getLogger(AppliactionCashe.class);

	private static AppliactionCashe appCashe = new AppliactionCashe();
	private static Map<String, byte[]> imgCashe;

	private AppliactionCashe() {
		if (imgCashe == null) {
			imgCashe = new HashMap<>();
		}
	}

	public static AppliactionCashe getInsatnce() {
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

	public void add(String key, Object value) {
		try {
			imgCashe.put(key, convertToBytes(value));
		} catch (IOException ex) {
			LOG.error(String.format("Convert object to bytes errror: {'%s'}",
					ex));
		}
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

	private byte[] convertToBytes(Object object) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutput out = new ObjectOutputStream(bos)) {
			out.writeObject(object);
			return bos.toByteArray();
		}
	}

	private Object convertFromBytes(byte[] bytes) {
		Object returnObj = null;
		if (!ArrayUtils.isEmpty(bytes)) {
			try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
					ObjectInput in = new ObjectInputStream(bis)) {
				returnObj = in.readObject();
			} catch (IOException | ClassNotFoundException ex) {
				LOG.error(String.format(
						"Convert from bytes to object errror: {'%s'}", ex));
			}
		}
		return returnObj;
	}
}
