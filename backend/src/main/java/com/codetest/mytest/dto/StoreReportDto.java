package com.codetest.mytest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StoreReportDto {
    List<StoreSummaryDto> stores;
}
