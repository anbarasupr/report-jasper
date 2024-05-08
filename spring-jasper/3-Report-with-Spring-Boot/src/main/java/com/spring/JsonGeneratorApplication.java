package com.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jasperreports.service.DataProviderService;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JsonGeneratorApplication {

	public static void main(String args[]) throws IOException {
		DataProviderService dataProviderService = new DataProviderService();

		writeToJsonFile(dataProviderService.getFlatStructedClass(), "FlatStructured.json");
		writeToJsonFile(dataProviderService.getRootObjectWithLists(), "ListsInsideRootObject.json");
		writeToJsonFile(dataProviderService.getRootObjectForReportWithSubreport(), "ReportWithSubreport.json");
	}

	private static void writeToJsonFile(Object data, String fileName) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String objectAsJson = objectMapper.writeValueAsString(data);
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, objectAsJson, "UTF-8");
	}

}
