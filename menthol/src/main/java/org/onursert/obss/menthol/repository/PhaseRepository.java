package org.onursert.obss.menthol.repository;

import org.onursert.obss.menthol.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer> {

    List<Phase> findByProgramId(Integer integer);
}
