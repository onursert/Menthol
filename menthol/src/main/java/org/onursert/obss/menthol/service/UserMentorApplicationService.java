package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.model.ProgramSubject;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.onursert.obss.menthol.repository.ProgramSubjectRepository;
import org.onursert.obss.menthol.repository.SubSubjectRepository;
import org.onursert.obss.menthol.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserMentorApplicationService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubSubjectRepository subSubjectRepository;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    ProgramSubjectRepository programSubjectRepository;

    public String userMentorApplication() {
        return "redirect:/user/mentor/application";
    }

    public String userMentorApplicationGet(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "user/mentor/application";
    }

    public String userMentorApplicationPost(String description, String subjectIdName) {
        if (description.equals("") || subjectIdName.equals("")){
            return "redirect:/user/index?error=null";
        }
        return "redirect:/user/mentor/application2?description=" + description + "&subjectId=" + subjectIdName.split(" ")[0];
    }

    public String userMentorApplication2Get(Model model, String description, String subjectId) {
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("description", description);
        model.addAttribute("subSubjects", subSubjectRepository.findBySubjectId(Integer.valueOf(subjectId)));
        return "user/mentor/application2";
    }

    public String userMentorApplication2Post(String description, String subjectId, String subSubjects) {
        if (description.equals("") || subjectId.equals("") || subSubjects.equals("")){
            return "redirect:/user/index?error=null";
        }
        if (programRepository.findByMentorUidAndEndedIsFalse(getCurrentUserName()).size() >= 2) {
            return "redirect:/user/index?error=max_mentor_count_for_user";
        }

        Program program = new Program();
        program.setMentorUid(getCurrentUserName());
        program.setDescription(description);
        program.setAccepted(Boolean.FALSE);
        program.setEnded(Boolean.FALSE);
        program.setRequestAccept(Boolean.FALSE);
        Program savedProgram = programRepository.save(program);

        String[] subSubjectsIdsNames = subSubjects.split(",");
        for (int i = 0; i < subSubjectsIdsNames.length; i++) {
            ProgramSubject programSubject = new ProgramSubject();
            programSubject.setProgram(savedProgram);
            programSubject.setSubject(subjectRepository.findById(Integer.valueOf(subjectId)).orElse(null));
            programSubject.setSubSubject(subSubjectRepository.findById(Integer.valueOf(subSubjectsIdsNames[i].split(" ")[0])).orElse(null));
            programSubjectRepository.save(programSubject);
        }

        return "redirect:/user/index?success=apply";
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
