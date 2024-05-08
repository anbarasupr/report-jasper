package com.spring.jasperreports.model.northwind;

import java.util.List;

 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Customer {
	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("PostalCode")
	private String postalCode;
	
	@JsonProperty("Region")
	private String region;
	
	@JsonProperty("ContactName")
	private String contactName;

	@JsonProperty("Fax")
	private String fax;

	@JsonProperty("Address")
	private String address;

	@JsonProperty("CustomerID")
	private String customerID;

	@JsonProperty("Company Name")
	private String companyName;

	@JsonProperty("Orders")
	private List<Order> orders;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("City")
	private String city;

	@JsonProperty("ContactTitle")
	private String contactTitle;

}