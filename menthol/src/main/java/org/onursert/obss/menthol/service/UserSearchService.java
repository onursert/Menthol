package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.model.ProgramSubject;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.onursert.obss.menthol.repository.ProgramSubjectRepository;
import org.onursert.obss.menthol.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSearchService {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    ProgramSubjectRepository programSubjectRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public String userSearchList(Model model, String searchFor) {
        String currentUser = getCurrentUserName();
        List<Program> foundProgram = programRepository.findByMenteeUidIsNullAndAcceptedIsTrueAndMentorUidContainsAndMentorUidIsNotOrMenteeUidIsNullAndAcceptedIsTrueAndDescriptionContainsAndMentorUidIsNot(searchFor, currentUser, searchFor, currentUser);
        List<ProgramSubject> allProgramSubjects = programSubjectRepository.findAll();

        for (ProgramSubject programSubject : allProgramSubjects) {
            Program fromProgramSubject = programSubject.getProgram();

            if (!foundProgram.contains(fromProgramSubject) &&
                    fromProgramSubject.getAccepted() == Boolean.TRUE &&
                    fromProgramSubject.getMenteeUid() == null &&
                    !fromProgramSubject.getMentorUid().equals(currentUser) &&
                    (programSubject.getSubject().getName().toLowerCase().contains(searchFor.toLowerCase()) ||
                            programSubject.getSubSubject().getName().toLowerCase().contains(searchFor.toLowerCase()))
            ) {
                foundProgram.add(fromProgramSubject);
            }

        }
        model.addAttribute("programs", foundProgram);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "user/search/list";
    }

    public String userSearchSubject(Model model, Integer subjectId) {
        List<Program> foundProgramList = new ArrayList<>();
        List<ProgramSubject> foundProgramSubjects = programSubjectRepository.findBySubjectId(subjectId);
        for (ProgramSubject programSubject : foundProgramSubjects) {
            Program foundProgram = programSubject.getProgram();
            if (foundProgram.getAccepted() == Boolean.TRUE &&
                    foundProgram.getMenteeUid() == null &&
                    !foundProgram.getMentorUid().equals(getCurrentUserName())) {
                foundProgramList.add(foundProgram);
            }
        }

        model.addAttribute("programs", foundProgramList);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "user/search/list";
    }

    public String userSearchSubSubject(Model model, Integer subSubjectId) {
        List<Program> foundProgramList = new ArrayList<>();
        List<ProgramSubject> foundProgramSubjects = programSubjectRepository.findBySubSubjectId(subSubjectId);
        for (ProgramSubject programSubject : foundProgramSubjects) {
            Program foundProgram = programSubject.getProgram();
            if (foundProgram.getAccepted() == Boolean.TRUE &&
                    foundProgram.getMenteeUid() == null &&
                    !foundProgram.getMentorUid().equals(getCurrentUserName())) {
                foundProgramList.add(foundProgram);
            }
        }

        model.addAttribute("programs", foundProgramList);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "user/search/list";
    }

    public String userSearchJoin(Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (programRepository.findByMenteeUidAndEndedIsFalse(getCurrentUserName()).size() >= 1) {
            return "redirect:/user/index?error=max_mentee_count_for_user";
        }
        if (program.getMenteeUid() != null) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getMentorUid().equals(currentUser)) {
            return "redirect:/user/index?error=cannot_enroll_own_program";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        program.setMenteeUid(currentUser);
        programRepository.save(program);
        return "redirect:/user/index?success=join";
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
