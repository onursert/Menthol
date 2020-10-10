package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminMoreService {

    @Autowired
    private ProgramRepository programRepository;

    public String adminMore() {
        return "redirect:/admin/more/program";
    }

    public String adminMoreProgram(Model model) {
        model.addAttribute("programs", programRepository.findByAcceptedIsTrue());
        return "/admin/more/program";
    }

    public String adminMoreApplication(Model model) {
        model.addAttribute("programs", programRepository.findByAcceptedIsFalse());
        return "/admin/more/application";
    }
}
