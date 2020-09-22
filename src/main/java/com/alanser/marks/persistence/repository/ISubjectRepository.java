package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Subject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {

  Optional<Subject> findByName(String name);
}
