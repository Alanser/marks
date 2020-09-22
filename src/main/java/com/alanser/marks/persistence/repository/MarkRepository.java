package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Mark;
import com.alanser.marks.persistence.model.Pupil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarkRepository {

  @Autowired
  IMarksRepository repository;

  public Mark save(Mark mark) {
    Mark retrieved = getByAllFields(mark);
    if (retrieved == null) {
      return repository.save(mark);
    }
    return retrieved;
  }

  public Mark getByAllFields(Mark mark) {
    return repository
        .findByGradeAndPupilAndSubjectAndMarkAndDate(mark.getGrade(), mark.getPupil(), mark.getSubject(),
            mark.getMark(), mark.getDate()).orElse(null);
  }

  public List<Mark> findAllByGrade(int grade) {
    return repository.findAllByGrade(grade);
  }

  public Map<String, Double> getAverageEstimate(int grade) {
    Map<String, Double> estimates = repository.getAverageEstimate(grade).stream()
        .collect(Collectors.toMap(o -> ((Pupil) o[0]).getFio(), o -> (Double) o[1]));
    if(estimates.isEmpty())
      return null;
    return estimates;
  }
}
