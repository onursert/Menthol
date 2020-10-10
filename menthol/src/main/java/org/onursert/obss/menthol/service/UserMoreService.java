package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserMoreService {

    @Autowired
    private ProgramRepository programRepository;

    public String userMore() {
        return "redirect:/user/more/mentor";
    }

    public String userMoreMentor(Model model) {
        model.addAttribute("currentUser", getCurrentUserName());
        model.addAttribute("programs", programRepository.findByMentorUid(getCurrentUserName()));
        return "/user/more/mentor";
    }

    public String userMoreMentee(Model model) {
        model.addAttribute("currentUser", getCurrentUserName());
        model.addAttribute("programs", programRepository.findByMenteeUid(getCurrentUserName()));
        return "/user/more/mentee";
    }

    public String userMoreProgram(Model model) {
        model.addAttribute("currentUser", getCurrentUserName());
        model.addAttribute("programs", programRepository.findByAcceptedIsTrueAndMentorUidOrAcceptedIsTrueAndMenteeUid(getCurrentUserName(), getCurrentUserName()));
        return "/user/more/program";
    }

    public String userMoreApplication(Model model) {
        model.addAttribute("currentUser", getCurrentUserName());
        model.addAttribute("programs", programRepository.findByAcceptedIsFalseAndMentorUid(getCurrentUserName()));
        return "/user/more/application";
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
