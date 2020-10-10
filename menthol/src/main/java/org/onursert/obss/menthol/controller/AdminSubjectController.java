package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.model.Subject;
import org.onursert.obss.menthol.service.AdminSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminSubjectController {

    @Autowired
    private AdminSubjectService adminSubjectService;

    @RequestMapping(value = "/admin/subject")
    public String adminSubject() {
        return adminSubjectService.adminSubject();
    }

    @RequestMapping(path = "/admin/subject/list", method = RequestMethod.GET)
    public String adminSubjectList(Model model,
                                   @RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "success", required = false) String success) {
        return adminSubjectService.adminSubjectList(model, error, success);
    }

    @RequestMapping(path = "/admin/subject/edit/{id}", method = RequestMethod.GET)
    public String adminSubjectEdit(Model model, @PathVariable(value = "id") Integer id) {
        return adminSubjectService.adminSubjectEdit(model, id);
    }

    @RequestMapping(path = "/admin/subject/list", method = RequestMethod.POST)
    public String adminSubjectUpdate(Subject subject) {
        return adminSubjectService.adminSubjectUpdate(subject);
    }

    @RequestMapping(path = "/admin/subject/delete/{id}", method = RequestMethod.GET)
    public String adminSubjectDelete(@PathVariable(value = "id") Integer id) {
        return adminSubjectService.adminSubjectDelete(id);
    }

    @RequestMapping(path = "/admin/subject/add", method = RequestMethod.GET)
    public String adminSubjectAdd() {
        return adminSubjectService.adminSubjectAdd();
    }

    @RequestMapping(path = "/admin/subject/add", method = RequestMethod.POST)
    public String adminSubjectAdd(@RequestParam(value = "subjectName") String subjectName, @RequestParam(value = "subSubjects", required = false) String subSubjects) {
        return adminSubjectService.adminSubjectAdd(subjectName, subSubjects);
    }
}
