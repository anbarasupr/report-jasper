package com.spring.jasperreports.model.listinroot;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
 
@AllArgsConstructor
@Builder
@Getter
public class AnotherNestedElement {
    private String caption;
    private String currency;
    private BigDecimal amount;
}
