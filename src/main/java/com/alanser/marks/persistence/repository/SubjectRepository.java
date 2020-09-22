package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepository {

  @Autowired
  private ISubjectRepository repository;

  public Subject save(Subject subject) {
    Subject retrieved = get(subject.getName());
    if (retrieved != null) {
      return retrieved;
    }
    return repository.save(subject);
  }

  public Subject get(String name) {
    return repository.findByName(name).orElse(null);
  }
}
