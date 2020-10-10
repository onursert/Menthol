package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.AdminMoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMoreController {

    @Autowired
    private AdminMoreService adminMoreService;

    @RequestMapping(path = "/admin/more", method = RequestMethod.GET)
    public String adminMore() {
        return adminMoreService.adminMore();
    }

    @RequestMapping(path = "/admin/more/program", method = RequestMethod.GET)
    public String adminMoreProgram(Model model) {
        return adminMoreService.adminMoreProgram(model);
    }

    @RequestMapping(path = "/admin/more/application", method = RequestMethod.GET)
    public String adminMoreApplication(Model model) {
        return adminMoreService.adminMoreApplication(model);
    }
}
