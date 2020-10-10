package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String admin() {
        return adminService.admin();
    }

    @RequestMapping(path = "/admin/index", method = RequestMethod.GET)
    public String adminIndex(Model model,
                             @RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "success", required = false) String success) {
        return adminService.adminIndex(model, error, success);
    }

    @RequestMapping(path = "/admin/index/accept/{id}", method = RequestMethod.GET)
    public String adminIndexAccept(@PathVariable(value = "id") Integer id) {
        return adminService.adminIndexAccept(id);
    }

    @RequestMapping(path = "/admin/index/decline/{id}", method = RequestMethod.GET)
    public String adminIndexDecline(@PathVariable(value = "id") Integer id) {
        return adminService.adminIndexDecline(id);
    }
}
