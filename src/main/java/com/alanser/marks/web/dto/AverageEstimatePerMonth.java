package com.alanser.marks.web.dto;

import java.time.Month;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AverageEstimatePerMonth {

  private String fio;
  Map<Month, Double> averageEstimatesPerMonth;
}
