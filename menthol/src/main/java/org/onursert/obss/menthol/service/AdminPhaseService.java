package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.repository.PhaseRepository;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminPhaseService {

    @Autowired
    PhaseRepository phaseRepository;

    @Autowired
    ProgramRepository programRepository;

    public String adminPhaseList(Model model, Integer programId) {
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/admin/index?error=null";
        }
        if (program.getMenteeUid() == null) {
            return "redirect:/admin/index?error=not_start";
        }

        model.addAttribute("program", program);
        model.addAttribute("phases", phaseRepository.findByProgramId(programId));
        return "admin/phase/list";
    }
}
