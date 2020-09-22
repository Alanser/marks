package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Mark;
import com.alanser.marks.persistence.model.Pupil;
import com.alanser.marks.persistence.model.Subject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMarksRepository extends JpaRepository<Mark, Long> {

  List<Mark> findAllByGrade(int grade);

  Optional<Mark> findByGradeAndPupilAndSubjectAndMarkAndDate(int grade, Pupil pupil, Subject subject, int mark,
      LocalDate date);

  @Query("SELECT m.pupil, AVG(m.mark) FROM Mark m WHERE m.grade=:grade GROUP BY m.pupil")
  List<Object[]> getAverageEstimate(@Param("grade") int grade);
}
