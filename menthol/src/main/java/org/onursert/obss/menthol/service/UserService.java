package org.onursert.obss.menthol.service;

import org.apache.commons.lang3.text.WordUtils;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    @Autowired
    private ProgramRepository programRepository;

    public String user() {
        return "redirect:/user/index";
    }

    public String userIndex(Model model, String error, String success) {
        if (success != null) {
            model.addAttribute("success", WordUtils.capitalizeFully(success.replaceAll("_", " ")));
        }
        if (error != null) {
            model.addAttribute("error", WordUtils.capitalizeFully(error.replaceAll("_", " ")));
        }
        model.addAttribute("currentUser", getCurrentUserName());
        model.addAttribute("programs", programRepository.findByMentorUidOrMenteeUid(getCurrentUserName(), getCurrentUserName()));
        return "user/index";
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
