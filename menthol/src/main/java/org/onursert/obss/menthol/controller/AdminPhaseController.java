package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.AdminPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminPhaseController {

    @Autowired
    private AdminPhaseService adminPhaseService;

    @RequestMapping(path = "/admin/phase/list/{programId}", method = RequestMethod.GET)
    public String adminPhaseList(Model model, @PathVariable(value = "programId") Integer programId) {
        return adminPhaseService.adminPhaseList(model, programId);
    }
}
