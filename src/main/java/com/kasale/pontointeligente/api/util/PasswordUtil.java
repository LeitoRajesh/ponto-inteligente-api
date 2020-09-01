package com.kasale.pontointeligente.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	private static final Logger log = (Logger) LoggerFactory.getLogger(PasswordUtil.class);
	
	/**
	 * Gera um hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public static String gerarBCrypt(final String senha) {
		String newPassword = null;
		if(senha != null) {
			log.info("Gerando hash com o BCrypt.");
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			newPassword = bCryptPasswordEncoder.encode(senha);			
		}
		return newPassword;
	}
	
}
