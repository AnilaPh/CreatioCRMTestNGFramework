package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// Common method to read the data from Excel and convert it into a 2D array.
	public static String[][] readData(String fileName, String sheetName) {

		// Placeholder for 2D array to hold the data read from Excel
		String[][] data = null;

		try {
			// Read the file within Java.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Use Apache POI to read the Excel file.
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Get the sheet from the workbook.
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the number of rows and columns in the sheet.
			int totalRows = sheet.getPhysicalNumberOfRows(); // only not-null rows
			int totalCols = sheet.getRow(0).getPhysicalNumberOfCells(); // only not-null cells in the first row

			// Initialize the 2D array
			data = new String[totalRows - 1][totalCols]; // excluding header row

			// Read the data from the sheet and store it in the 2D array
			for (int r = 1; r < totalRows; r++) { // start from 1 to skip header row
				for (int c = 0; c < totalCols; c++) {
					data[r - 1][c] = sheet.getRow(r).getCell(c).getStringCellValue();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	// Common method to read the data from Excel and convert it into List<Map<String, String>>
	public static List<Map<String, String>> readExcelData(String fileName, String sheetName) {

		// Placeholder for List<Map<String, String>> to hold the data read from Excel
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		try {
			// Read the file within Java.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Use Apache POI to read the Excel file.
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Get the sheet from the workbook.
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the number of rows and columns in the sheet.
			int totalRows = sheet.getPhysicalNumberOfRows(); // only not-null rows
			int totalCols = sheet.getRow(0).getPhysicalNumberOfCells(); // only not-null cells in the first row

			// Read the data from the sheet and store it in the 2D array
			for (int r = 1; r < totalRows; r++) {
				
				// Create a new map for each row
				Map<String, String> rowData = new HashMap<String, String>();				
				
				for (int c = 0; c < totalCols; c++) {
					// Get the header value
					String key = sheet.getRow(0).getCell(c).getStringCellValue();//get header from first row always
					
					// Get the cell value
					String value = sheet.getRow(r).getCell(c).getStringCellValue();//get cell value from current row
					
					// Put the key-value pair in the map
					rowData.put(key, value);					
				}	
				
				// Add the map to the list
				data.add(rowData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}