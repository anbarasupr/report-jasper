package com.spring.jasperreports.generator;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jasperreports.exception.CannotCompileReportException;
import com.spring.jasperreports.exception.CannotExportJasperPrintToPdf;
import com.spring.jasperreports.exception.CannotFillReportWithDataException;
import com.spring.jasperreports.exception.CannotPrepareJsonDataSourceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.data.JsonQLDataSource;

public class JasperGenerator {

	public byte[] generatePdf(String resourceName, Object data) {
		return generatePdf(resourceName, data, Collections.emptyList(), new HashMap<>());
	}

	public byte[] generatePdf(String resourceName, Object data, List<Subreport> subReportList) {
		return generatePdf(resourceName, data, subReportList, new HashMap<>());
	}

	public byte[] generatePdf(String resourceName, Object data, Map<String, Object> parametersMap) {
		return generatePdf(resourceName, data, Collections.emptyList(), parametersMap);
	}

	public byte[] generatePdf(String resourceName, Object data, List<Subreport> subReportList,
			Map<String, Object> parametersMap) {
		JasperReport compiledReport = compileReport(resourceName);
		for (Subreport subreport : subReportList) {
			JasperReport compiledSubReport = compileReport(subreport.getResourceName());
			parametersMap.put(subreport.getParameterName(), compiledSubReport);
		}
		JasperPrint filledJasperPrint = fillReport(compiledReport, getJsonDataSource(data), parametersMap);
		try {
			return JasperExportManager.exportReportToPdf(filledJasperPrint);
		} catch (JRException e) {
			throw new CannotExportJasperPrintToPdf("Cannot export compiled and filled report to PDF", e);
		}
	}

	private JasperReport compileReport(String resourceName) {
		try {
			return JasperCompileManager.compileReport(getClass().getResourceAsStream(resourceName));
		} catch (JRException e) {
			throw new CannotCompileReportException("Cannot compile provided JRXML to Jasper Report", e);
		}
	}

	private JasperPrint fillReport(JasperReport compiledReport, JRDataSource jsonDataSource,
			Map<String, Object> parametersMap) {
		try {
			System.out.println("parametersMap:: "+parametersMap);
//			return JasperFillManager.fillReport(compiledReport, parametersMap, jsonDataSource);
			return JasperFillManager.fillReport(compiledReport, parametersMap);
		} catch (JRException e) {
			throw new CannotFillReportWithDataException("Cannot fill report with given data", e);
		}
	}

	private JsonDataSource getJsonDataSource(Object data) {
		try {
			if(data == null) {
				return null;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			String objectAsJson = objectMapper.writeValueAsString(data);
			System.out.println("JsonDataSource objectAsJson: " + objectAsJson);
			InputStream in = IOUtils.toInputStream(objectAsJson, "UTF-8");
//			return new JsonDataSource(in);
			return new JsonDataSource(in);
		} catch (Exception e) {
			throw new CannotPrepareJsonDataSourceException("Cannot prepare JSON Data Source with given data", e);
		}

	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Subreport {
		/**
		 * Name of the parameter that is used in main report under "subreportExpression"
		 */
		private String parameterName;

		/**
		 * Name of the resource that contains subreport JRXML file
		 */
		private String resourceName;
	}

}
