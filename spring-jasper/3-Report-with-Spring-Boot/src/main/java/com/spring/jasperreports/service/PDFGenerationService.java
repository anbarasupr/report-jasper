package com.spring.jasperreports.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jasperreports.generator.JasperGenerator;
import com.spring.jasperreports.model.listinroot.RootObject;
import com.spring.jasperreports.model.listinroot.RootObjectForReportWithSubreport;
import com.spring.jasperreports.model.northwind.NorthwindCustomerOrders;
import com.spring.jasperreports.model.simple.FlatStructuredClass;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.query.JsonQLQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;

public class PDFGenerationService {

	private JasperGenerator jasperGenerator;
	private DataProviderService dataProviderService;

	public PDFGenerationService() {
		this.jasperGenerator = new JasperGenerator();
		this.dataProviderService = new DataProviderService();
	}

	public void generatePdfWithFlatStructuredData() {
		FlatStructuredClass flatStructuredClass = dataProviderService.getFlatStructedClass();
		byte[] pdfBytes = jasperGenerator.generatePdf("/templates/FlatStructuredTemplate.jrxml", flatStructuredClass);
		saveToFile(pdfBytes, "FlatStructured.pdf");
	}

	public void generatePdfWithListsInsideRootObject() {
		RootObject rootObject = dataProviderService.getRootObjectWithLists();
		byte[] pdfBytes = jasperGenerator.generatePdf("/templates/ListsInsideRootObjectsTemplate.jrxml", rootObject);
		saveToFile(pdfBytes, "ListsInsideRootObject.pdf");
	}

	public void generatePdfWithCustomFont() {
		FlatStructuredClass flatStructuredClass = dataProviderService.getFlatStructedClass();
		byte[] pdfBytes = jasperGenerator.generatePdf("/templates/CustomFontTemplate.jrxml", flatStructuredClass);
		saveToFile(pdfBytes, "CustomFont.pdf");
	}

	public void generatePdfWithReportWithSubreport() {
		RootObjectForReportWithSubreport rootObjectForReportWithSubreport = dataProviderService
				.getRootObjectForReportWithSubreport();
		byte[] pdfBytes = jasperGenerator
				.generatePdf("/templates/ReportWithSubreportTemplate.jrxml", rootObjectForReportWithSubreport,
						Arrays.asList(JasperGenerator.Subreport.builder()
								.resourceName("/templates/SubreportForSectionTemplate.jrxml")
								.parameterName("SectionSubReportTemplate").build()));
		saveToFile(pdfBytes, "ReportWithSubreport.pdf");
	}

	private void saveToFile(byte[] bytes, String fileName) {
		try {
			File file = new File(fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generatePdfWithJsoNQLNorthwind() {
		Object data = dataProviderService.convertDTO(NorthwindCustomerOrders.class,
				"/json/jsonqldatasource/northwind.json");
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(JsonQLQueryExecuterFactory.JSON_DATE_PATTERN, "yyyy-MM-dd");
//		params.put(JsonQLQueryExecuterFactory.JSON_NUMBER_PATTERN, "#,##0.##");
		params.put(JsonQLQueryExecuterFactory.JSON_LOCALE, Locale.ENGLISH);
		params.put(JRParameter.REPORT_LOCALE, Locale.US);
		try {
			String objectAsJson = new ObjectMapper().writeValueAsString(data);
			InputStream in = IOUtils.toInputStream(objectAsJson, "UTF-8");
			System.out.println("generatePdfWithJsoNQLNorthwind objectAsJson: " + objectAsJson);
//			params.put(JsonQueryExecuterFactory.JSON_SOURCE, objectAsJson);
//			params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] pdfBytes = jasperGenerator.generatePdf("/templates/jsonqldatasource/NorthwindOrdersReport.jrxml", data, params);
		saveToFile(pdfBytes, "NorthwindCustomerOrders.pdf");
	}

	public void generatePdfWithJsonNorthwind() {
		Object data = dataProviderService.convertDTO(NorthwindCustomerOrders.class,
				"/json/jsondatasource/northwind.json");
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(JsonQueryExecuterFactory.JSON_DATE_PATTERN, "yyyy-MM-dd");
//		params.put(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, "#,##0.##");
//		params.put(JsonQueryExecuterFactory.JSON_LOCALE, Locale.ENGLISH);
//		params.put(JRParameter.REPORT_LOCALE, Locale.US);
		try {
			String objectAsJson = new ObjectMapper().writeValueAsString(data);
			InputStream in = IOUtils.toInputStream(objectAsJson, "UTF-8");
			System.out.println("generatePdfWithJsonNorthwind objectAsJson: " + objectAsJson);
			params.put(JsonQueryExecuterFactory.JSON_SOURCE, objectAsJson);
			params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] pdfBytes = jasperGenerator.generatePdf("/templates/jsondatasource/JsonCustomersReport.jrxml", data,
				Arrays.asList(JasperGenerator.Subreport.builder()
						.resourceName("/templates/jsondatasource/JsonOrdersReport.jrxml")
						.parameterName("subTemplateReport").build()),
				params);
		saveToFile(pdfBytes, "JsonCustomersReport.pdf");
	}
}
