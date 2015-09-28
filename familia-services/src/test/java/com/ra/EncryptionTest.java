package com.ra;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ra.familia.services.EncryptionService;

import junit.framework.TestCase;

@Ignore
public class EncryptionTest extends TestCase {

	private final static String PASSWORD = "BuenosAires";
	private EncryptionService encryptionService;
	private String expectedResult = "iUn73cLHIQohK24tmzrGap78GxluRcNInqzA1kVxPz8=";

	@Override
	@Before
	public void setUp() {
		encryptionService = new EncryptionService();
	}

	@Ignore
	@Test
	public void testEncoding() throws NoSuchAlgorithmException, IOException {
		String actualResult = encryptionService.encode(PASSWORD);
		assertEquals(expectedResult, actualResult);
	}

	@Ignore
	@Test
	public void testDecoding() throws NoSuchAlgorithmException, IOException {
		String actualResult = encryptionService.decodeBase64(expectedResult);
		assertEquals("ç„;#;KP0Ür}èL/$u±ÑXpæ2QH§è›¾", actualResult);
	}

}
