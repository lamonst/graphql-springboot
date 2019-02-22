package com.graphql.local.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtils {

	private static Logger logger = Logger.getLogger(PropertiesUtils.class.getPackage().getName());
	private static final Properties PROP = new Properties();

	public static Object obterPropriedades(String fileName, String keyPropriedade) throws Exception {
		try {
			PROP.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
			Object valorPropriedade = PROP.getProperty(keyPropriedade);
			logger.info("Valor Arquivo: " + valorPropriedade);
			return valorPropriedade;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error: "+ e.getMessage());//, e);
			throw e;
		}
	}

	public static Object obterPropriedades(String keyPropriedade) throws Exception {

		return System.getProperty(keyPropriedade);
	}

	public static Object obterPropriedades(InputStream arquivo, String keyPropriedade) throws Exception {
		try {
			Properties prop = new Properties();
			prop.load(arquivo);
			Object valorPropriedade = prop.getProperty(keyPropriedade);
			logger.log(Level.INFO, "Valor Arquivo: " + valorPropriedade);
			return valorPropriedade;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error: "+ e.getMessage());//, e);
			throw e;
		}
	}
}