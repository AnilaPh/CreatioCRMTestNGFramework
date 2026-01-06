package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtil {

	// Common method to read the data from PDF file.
	public static String readData(String fileName, int startPage, int endPage) {

		// Create a variable to store the data from the PDF file.
		String data = "";

		try {
			// Read the PDF file from the specific folder.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Files\\" + fileName);

			// Load the file into PDFDocument class to analyze PDF file
			PDDocument document = PDDocument.load(fis);

			// Extract the text from PDF file using PDFTextripper class by specifying page
			// numbers.
			PDFTextStripper pdf = new PDFTextStripper();
			pdf.setStartPage(startPage);
			pdf.setEndPage(endPage);

			// Get the data from the PDF file and store it in the variable.
			data = pdf.getText(document);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	// Common method to read the data from PDF file.
	public static String readProtectedPDF(String fileName,String password, int startPage, int endPage) {

		// Create a variable to store the data from the PDF file.
		String data = "";

		try {
			// Read the PDF file from the specific folder.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Files\\" + fileName);

			// Load the file into PDFDocument class to analyze PDF file
			PDDocument document = PDDocument.load(fis, password);

			// Extract the text from PDF file using PDFTextripper class by specifying page
			// numbers.
			PDFTextStripper pdf = new PDFTextStripper();
			pdf.setStartPage(startPage);
			pdf.setEndPage(endPage);

			// Get the data from the PDF file and store it in the variable.
			data = pdf.getText(document);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

}