package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserRequestService {

    @Autowired
    ProgramRepository programRepository;

    public String userRequestAccept(Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_accepted";
        }
        if (program.getMenteeUid() == null) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getMentorUid().equals(currentUser)) {
            return "redirect:/user/index?error=not_auth";
        }
        program.setRequestAccept(Boolean.TRUE);
        program.setStartDate((int) (Instant.now().getEpochSecond()));
        programRepository.save(program);
        return "redirect:/user/index?success=accept";
    }

    public String userRequestDecline(Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_accepted";
        }
        if (program.getMenteeUid() == null) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getMentorUid().equals(currentUser)) {
            return "redirect:/user/index?error=not_auth";
        }
        program.setRequestAccept(Boolean.FALSE);
        program.setStartDate(null);
        program.setMenteeUid(null);
        programRepository.save(program);
        return "redirect:/user/index?success=decline";
    }

    public String userRequestProgramEnd(Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_accepted";
        }
        if (program.getMenteeUid() == null) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getMentorUid().equals(currentUser) && !program.getMenteeUid().equals(currentUser)) {
            return "redirect:/user/index?error=not_auth";
        }
        program.setEnded(Boolean.TRUE);
        programRepository.save(program);
        return "redirect:/user/index?success=ended";
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
