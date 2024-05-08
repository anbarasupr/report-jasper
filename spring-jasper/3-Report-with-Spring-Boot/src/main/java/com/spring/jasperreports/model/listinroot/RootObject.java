package com.spring.jasperreports.model.listinroot;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

 
@AllArgsConstructor
@Builder
@Getter
public class RootObject {
    private String name;
    private String surname;
    private BigDecimal amount;
    private Integer numberOfDays;

    private List<NestedElement> nestedElements;
    private List<AnotherNestedElement> anotherNestedElements;

}
