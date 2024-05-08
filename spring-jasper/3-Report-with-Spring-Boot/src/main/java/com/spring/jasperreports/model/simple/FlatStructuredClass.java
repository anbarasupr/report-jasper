package com.spring.jasperreports.model.simple;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

 
@AllArgsConstructor
@Builder
@Getter
public class FlatStructuredClass {
    private String name;
    private String surname;
    private BigDecimal amount;
    private Integer numberOfDays;
}
