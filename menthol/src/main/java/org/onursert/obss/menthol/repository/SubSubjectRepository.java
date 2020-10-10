package org.onursert.obss.menthol.repository;

import org.onursert.obss.menthol.model.SubSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSubjectRepository extends JpaRepository<SubSubject, Integer> {

    List<SubSubject> findBySubjectId(Integer subjectId);
}
