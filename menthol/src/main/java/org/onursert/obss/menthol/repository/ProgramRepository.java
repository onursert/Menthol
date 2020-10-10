package org.onursert.obss.menthol.repository;

import org.onursert.obss.menthol.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

    List<Program> findByMentorUidOrMenteeUid(String mentorUid, String menteeUid);

    List<Program> findByMentorUid(String mentorUid);

    List<Program> findByMenteeUid(String menteeUid);

    List<Program> findByMenteeUidIsNullAndAcceptedIsTrueAndMentorUidContainsAndMentorUidIsNotOrMenteeUidIsNullAndAcceptedIsTrueAndDescriptionContainsAndMentorUidIsNot(String mentorUid, String currentUser1, String description, String currentUser2);

    List<Program> findByAcceptedIsTrue();

    List<Program> findByAcceptedIsFalse();

    List<Program> findByAcceptedIsTrueAndMentorUidOrAcceptedIsTrueAndMenteeUid(String mentorUid, String menteeUid);

    List<Program> findByAcceptedIsFalseAndMentorUid(String mentorUid);

    List<Program> findByMenteeUidAndEndedIsFalse(String menteeUid);

    List<Program> findByMentorUidAndEndedIsFalse(String mentorUid);
}
