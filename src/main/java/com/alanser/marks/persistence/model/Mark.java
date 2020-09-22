package com.alanser.marks.persistence.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "marks", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"grade", "pupil_id", "subject_id", "mark", "date"})
})
public class Mark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private int grade;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pupil_id", nullable = false)
  private Pupil pupil;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_id", nullable = false)
  private Subject subject;

  @Column
  private int mark;

  @Column
  private LocalDate date;

  public Mark(int grade, Pupil pupil, Subject subject, int mark, LocalDate date) {
    this.grade = grade;
    this.pupil = pupil;
    this.subject = subject;
    this.mark = mark;
    this.date = date;
  }
}
