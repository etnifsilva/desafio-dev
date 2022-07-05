package com.codetest.mytest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class StoreSummaryDto {
    private String name;
    private BigDecimal total;
}
