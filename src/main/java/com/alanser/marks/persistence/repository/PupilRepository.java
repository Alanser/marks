package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Pupil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PupilRepository {

  @Autowired
  private IPupilRepository repository;

  public Pupil save(Pupil pupil) {
    Pupil retrieved = get(pupil.getFio());
    if (retrieved != null) {
      return retrieved;
    }
    return repository.save(pupil);
  }

  public Pupil get(String fio) {
    return repository.findByFio(fio).orElse(null);
  }

}
