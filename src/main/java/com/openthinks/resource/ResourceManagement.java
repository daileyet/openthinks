/**
 * 
 */
package com.openthinks.resource;

import java.io.InputStream;

import com.openthinks.libs.utilities.Checker;

/**
 * @author minjdai
 * 
 */
public class ResourceManagement {

	public static InputStream getResource(String resourceName) {
		return ResourceManagement.class.getResourceAsStream(resourceName);
	}

	public static InputStream getDbResource() {
		InputStream config = getResource("dbconfig.properties");
		config = (config == null) ? getResource("/dbconfig.properties") : config;
		Checker.require(config).notNull("DB configure file not found.");
		return config;
	}

	public static InputStream getLogResource() {
		InputStream config = getResource("log4j.properties");
		config = (config == null) ? getResource("/log4j.properties") : config;
		Checker.require(config).notNull("Log configure file not found.");
		return config;
	}
}
