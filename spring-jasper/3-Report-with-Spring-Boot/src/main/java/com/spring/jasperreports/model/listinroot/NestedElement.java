package com.spring.jasperreports.model.listinroot;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
 
@AllArgsConstructor
@Builder
@Getter
public class NestedElement {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
