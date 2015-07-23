package com.ra.familia.services;

import java.util.HashMap;
import java.util.Map;

public class AppliactionCashe {
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

	public byte[] get(String key) {
		return imgCashe.get(key);
	}

	public void add(String key,byte[] value)
	{
		imgCashe.put(key, value);
	}

}
