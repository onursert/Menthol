package org.onursert.obss.menthol.repository;

import org.onursert.obss.menthol.model.ProgramSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramSubjectRepository extends JpaRepository<ProgramSubject, Integer> {

    List<ProgramSubject> findBySubjectId(Integer subjectId);

    List<ProgramSubject> findBySubSubjectId(Integer subSubjectId);
}
