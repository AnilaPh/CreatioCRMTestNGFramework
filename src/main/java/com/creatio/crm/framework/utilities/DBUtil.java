package com.creatio.crm.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtil {
	
	static Properties prop = PropUtil.readData("Config.properties");
	
	//Common method to connect with database and get the raw data from the database tables.
	public static ResultSet executeQuery(String query) throws SQLException {		
		ResultSet dataSet = null;
		String username = prop.getProperty("DB_USER");
		String password = prop.getProperty("DB_PASSWORD");
		String url = prop.getProperty("DB_URL");
		Connection connection = DriverManager.getConnection(url, username, password);
		dataSet = connection.createStatement().executeQuery(query);
		return dataSet;	
	}	

}