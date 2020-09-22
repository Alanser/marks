package com.alanser.marks.persistence.repository;

import com.alanser.marks.persistence.model.Pupil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPupilRepository extends JpaRepository<Pupil, Long> {

  Optional<Pupil> findByFio(String fio);
}
