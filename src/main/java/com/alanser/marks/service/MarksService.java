package com.alanser.marks.service;

import com.alanser.marks.persistence.model.Mark;
import com.alanser.marks.persistence.model.Pupil;
import com.alanser.marks.persistence.model.Subject;
import com.alanser.marks.persistence.repository.MarkRepository;
import com.alanser.marks.persistence.repository.PupilRepository;
import com.alanser.marks.persistence.repository.SubjectRepository;
import com.alanser.marks.web.dto.AverageEstimatePerMonth;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class MarksService implements IMarksService {

  @Autowired
  private MarkRepository markRepository;

  @Autowired
  private PupilRepository pupilRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public List<AverageEstimatePerMonth> getAverageRatingsByMonth(int grade) {
    List<Mark> marks = markRepository.findAllByGrade(grade);
    if (marks.isEmpty()) {
      throw new EntityNotFoundException("Класс не найден");
    }
    return marks.stream()
        .collect(Collectors.groupingBy(Mark::getPupil,
            Collectors.groupingBy(m -> m.getDate().getMonth(), Collectors.averagingDouble(Mark::getMark))))
        .entrySet().stream()
        .map(e -> new AverageEstimatePerMonth(e.getKey().getFio(), e.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public void importMarks(MultipartFile file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] arr = line.split(";");
        int grade = Integer.parseInt(arr[0]);
        Pupil pupil = pupilRepository.save(new Pupil(arr[1]));
        Subject subject = subjectRepository.save(new Subject(arr[2]));
        int mark = Integer.parseInt(arr[3]);
        LocalDate date = LocalDate.parse(arr[4]);
        markRepository.save(new Mark(grade, pupil, subject, mark, date));
      }
    }
  }

  public Map<String, Double> getAverageEstimate(int grade) {
    Map<String, Double> estimates = markRepository.getAverageEstimate(grade);
    if (estimates == null) {
      throw new EntityNotFoundException("Класс не найден");
    }
    return estimates;
  }

}
