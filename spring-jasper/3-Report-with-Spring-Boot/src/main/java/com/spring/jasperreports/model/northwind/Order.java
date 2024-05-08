package com.spring.jasperreports.model.northwind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Order {
	@JsonProperty("OrderID")
	private Long orderID;

//	@JsonProperty("OrderDate")
//	private String orderDate;
//
//	@JsonProperty("ShippedDate")
//	private String shippedDate;
//
//	@JsonProperty("RequiredDate")
//	private String requiredDate;

	@JsonProperty("Freight")
	private Float freight;

	@JsonProperty("EmployeeID")
	private Long employeeID;

	@JsonProperty("ShipVia")
	private Integer shipVia;
}