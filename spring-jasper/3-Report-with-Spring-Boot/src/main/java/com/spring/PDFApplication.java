package com.spring;

import com.spring.jasperreports.service.PDFGenerationService;

public class PDFApplication {
	static {
		System.setProperty("java.awt.headless", "true");
	}

	public static void main(String args[]) {
		PDFGenerationService service = new PDFGenerationService();
//		service.generatePdfWithFlatStructuredData();
//		service.generatePdfWithListsInsideRootObject();
//		service.generatePdfWithCustomFont();
//		service.generatePdfWithReportWithSubreport();

//		service.generatePdfWithJsoNQLNorthwind();
		
		service.generatePdfWithJsonNorthwind();
	}

}
