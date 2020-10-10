package org.onursert.obss.menthol.service;

import org.apache.commons.lang3.text.WordUtils;
import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminService {

    @Autowired
    private ProgramRepository programRepository;

    public String admin() {
        return "redirect:/admin/index";
    }

    public String adminIndex(Model model, String error, String success) {
        if (success != null) {
            model.addAttribute("success", WordUtils.capitalizeFully(success.replaceAll("_", " ")));
        }
        if (error != null) {
            model.addAttribute("error", WordUtils.capitalizeFully(error.replaceAll("_", " ")));
        }
        model.addAttribute("programs", programRepository.findAll());
        return "admin/index";
    }

    public String adminIndexAccept(Integer id) {
        Program foundProgram = programRepository.findById(id).orElse(null);
        try {
            foundProgram.setAccepted(Boolean.TRUE);
            programRepository.save(foundProgram);
        } catch (Exception e) {
            return "redirect:/admin/index?error=accept";
        }
        return "redirect:/admin/index?success=accept";
    }

    public String adminIndexDecline(Integer id) {
        Program foundProgram = programRepository.findById(id).orElse(null);
        try {
            foundProgram.setAccepted(Boolean.FALSE);
            programRepository.save(foundProgram);
        } catch (Exception e) {
            return "redirect:/admin/index?error=decline";
        }
        return "redirect:/admin/index?success=decline";
    }
}
