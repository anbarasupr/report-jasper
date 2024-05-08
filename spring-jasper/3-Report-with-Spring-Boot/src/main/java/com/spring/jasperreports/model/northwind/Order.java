package com.spring.jasperreports.model.northwind;

import java.util.Date;

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

	@JsonProperty("OrderDate")
	private Date orderDate;

	@JsonProperty("ShippedDate")
	private Date shippedDate;

	@JsonProperty("RequiredDate")
	private Date requiredDate;

	@JsonProperty("Freight")
	private Float freight;

	@JsonProperty("EmployeeID")
	private Long employeeID;

	@JsonProperty("ShipVia")
	private Integer shipVia;
}