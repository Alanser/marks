package com.alanser.marks.service;

import com.alanser.marks.web.dto.AverageEstimatePerMonth;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IMarksService {
    List<AverageEstimatePerMonth> getAverageRatingsByMonth(int grade);
    void importMarks(MultipartFile file) throws IOException;
}
