package com.alanser.marks.web.controller;

import com.alanser.marks.service.MarksService;
import com.alanser.marks.web.dto.AverageEstimatePerMonth;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MarksController {

  @Autowired
  private MarksService marksService;

  @PostMapping("/upload")
  public String importMarks(@RequestParam("file") MultipartFile file) throws IOException {
    marksService.importMarks(file);
    return "Marks imported successfully";
  }

  @GetMapping("/average-estimate/{grade}")
  public Map<String, Double> getAverageEstimate(@PathVariable("grade") int grade) {
    return marksService.getAverageEstimate(grade);
  }

  @GetMapping("/average-estimate-per-month/{grade}")
  public List<AverageEstimatePerMonth> getAverageEstimatePerMonth(@PathVariable("grade") int grade) {
    return marksService.getAverageRatingsByMonth(grade);
  }
}
