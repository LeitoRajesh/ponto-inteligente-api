package com.kasale.pontointeligente.api.utils;import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kasale.pontointeligente.api.util.PasswordUtil;

public class PasswordUtilsTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNula() {
		
		assertNull(PasswordUtil.gerarBCrypt(null));
	}
	
	@Test
	public void testGerarHashSenha()throws Exception{
		
		String hash = PasswordUtil.gerarBCrypt(SENHA);
		
		assertTrue(bCryptPasswordEncoder.matches(SENHA, hash));
	}
}
