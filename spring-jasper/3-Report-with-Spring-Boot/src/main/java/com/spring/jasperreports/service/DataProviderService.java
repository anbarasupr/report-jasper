package com.spring.jasperreports.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jasperreports.model.listinroot.AnotherNestedElement;
import com.spring.jasperreports.model.listinroot.NestedElement;
import com.spring.jasperreports.model.listinroot.RootObject;
import com.spring.jasperreports.model.listinroot.RootObjectForReportWithSubreport;
import com.spring.jasperreports.model.simple.FlatStructuredClass;

public class DataProviderService {

	public FlatStructuredClass getFlatStructedClass() {
		return FlatStructuredClass.builder().name("John").surname("Doe").amount(BigDecimal.valueOf(16.89))
				.numberOfDays(15).build();
	}

	public RootObject getRootObjectWithLists() {
		return RootObject.builder().name("John").surname("Doe").amount(BigDecimal.valueOf(16.89)).numberOfDays(15)
				.nestedElements(Arrays.asList(
						NestedElement.builder().name("Nested Element 1").price(BigDecimal.valueOf(10.4)).quantity(10)
								.build(),
						NestedElement.builder().name("Nested Element 2").price(BigDecimal.valueOf(7.4)).quantity(99)
								.build()))
				.anotherNestedElements(Arrays.asList(
						AnotherNestedElement.builder().caption("First Caption").currency("GBP")
								.amount(BigDecimal.valueOf(100)).build(),
						AnotherNestedElement.builder().caption("Second Caption").currency("USD")
								.amount(BigDecimal.valueOf(7)).build()))
				.build();
	}

	public RootObjectForReportWithSubreport getRootObjectForReportWithSubreport() {
		return RootObjectForReportWithSubreport.builder().name("John").surname("Doe").amount(BigDecimal.valueOf(16.89))
				.numberOfDays(15)
				.bigDebts(Arrays.asList(
						NestedElement.builder().name("BigDebt 1").price(BigDecimal.valueOf(500)).quantity(1).build(),
						NestedElement.builder().name("BigDebt 2").price(BigDecimal.valueOf(800)).quantity(1).build()))
				.smallDebts(Arrays.asList(
						NestedElement.builder().name("SmallDebt 1").price(BigDecimal.valueOf(10)).quantity(1).build(),
						NestedElement.builder().name("SmallDebt 2").price(BigDecimal.valueOf(8)).quantity(1).build()))
				.build();
	}

	public Object convertDTO(Class<?> c, String path) {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		InputStream is = c.getResourceAsStream(path);
		try {
			Object obj = mapper.readValue(is, c);
			System.out.println("convertDTO json :::::::::::::::::::::::::::::: " + obj);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
