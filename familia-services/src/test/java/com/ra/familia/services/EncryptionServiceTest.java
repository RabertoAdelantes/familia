package com.ra.familia.services;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class EncryptionServiceTest  {
	
	
	private EncryptionService encryptionService = new EncryptionService();
	
	@Test
	public void test() {
		String actualRes = encryptionService.encode("AdminPass!23$");
		System.out.println(actualRes);
		assertEquals("77+9W++/vdaZTu+/ve+/ve+/vVfvv70x77+9DhDvv71cfu+/vUpLLmEeXO+/vRjvv73vv71D77+9",actualRes);
	}

}
