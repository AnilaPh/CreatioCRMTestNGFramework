package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropUtil {

	// Common method to read the data from properties file.
	public static Properties readData(String fileName) {

		// Initialize the Properties class.
		Properties prop = new Properties(); // prop is null

		try {

			// Read the properties file by using FileInputStream class
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Config\\" + fileName);

			// Load the properties into 'prop' variable
			prop.load(fis);

		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return prop;
	}

}