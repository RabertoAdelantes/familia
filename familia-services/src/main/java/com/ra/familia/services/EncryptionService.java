package com.ra.familia.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ra.familia.dao.PropertiesManager;
@Service("encryptionService")
public class EncryptionService {

	private static final String CHARSET = "UTF-8";
	private static final String ALGORITHMUS = "SHA-256";
	private final static int ITERATION = 100;

	private static final Properties mailServerProperties = PropertiesManager
			.getMailProperties();
	private static final String SALT = mailServerProperties
			.getProperty("mail.key-salt");

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonServiceImpl.class);

	public String decodeBase64(String inputValue) {
		return new String(Base64.getDecoder().decode(inputValue.getBytes()));
	}

	public String encode(String password) {
		byte[] btPass = new byte[] {};
		if (password != null) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(ALGORITHMUS);
				digest.reset();
				digest.update(SALT.getBytes());

				btPass = digest.digest(password.getBytes(CHARSET));
				for (int i = 0; i < ITERATION; i++) {
					digest.reset();
					btPass = digest.digest(btPass);
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException exep) {
				LOG.error("Encoding exception :" + exep.getMessage());
			}
		}
		return encodeBase64(new String(btPass));
	}

	private String encodeBase64(String inputValue) {
		return new String(Base64.getEncoder().encode(inputValue.getBytes()));
	}

}
