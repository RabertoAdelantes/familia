package com.ra.familia.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesManager {
	private static final Logger LOG = LoggerFactory
			.getLogger(PropertiesManager.class);

	private static Properties jdbcProperties;
	private static Properties commonProperties;
	private static Properties mailProperties;

	static {
		jdbcProperties = new Properties();
		try (InputStream in = PropertiesManager.class
				.getResourceAsStream("../../../../jdbc.properties")) {
			jdbcProperties.load(in);
			in.close();
		} catch (IOException ioex) {
			LOG.error(ioex.getLocalizedMessage());
		}
		
		commonProperties = new Properties();
		try (InputStream in = PropertiesManager.class
				.getResourceAsStream("../../../../common.properties")) {
			commonProperties.load(in);
			in.close();
		} catch (IOException ioex) {
			LOG.error(ioex.getLocalizedMessage());
		}
		
		mailProperties = new Properties();
		try (InputStream in = PropertiesManager.class
				.getResourceAsStream("../../../../mail.properties")) {
			mailProperties.load(in);
			in.close();
		} catch (IOException ioex) {
			LOG.error(ioex.getLocalizedMessage());
		}

	}

	public static Properties getJdbcProperties()
	{
		return jdbcProperties;
	}
	
	public static Properties getCommonProperties()
	{
		return commonProperties;
	}
	
	public static Properties getMailProperties()
	{
		return mailProperties;
	}
}