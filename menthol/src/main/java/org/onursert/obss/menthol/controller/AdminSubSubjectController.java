package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.AdminSubSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminSubSubjectController {

    @Autowired
    private AdminSubSubjectService adminSubSubjectService;

    @RequestMapping(path = "/admin/subject/sub_subject_update", method = RequestMethod.POST)
    public String adminSubSubjectUpdate(@RequestParam(value = "subjectId") String subjectId,
                                        @RequestParam(value = "subSubjectId") String subSubjectId,
                                        @RequestParam(value = "subSubjectName") String subSubjectName,
                                        @RequestParam(value = "newSubSubjects") String newSubSubjects
    ) {
        return adminSubSubjectService.adminSubSubjectUpdate(subjectId, subSubjectId, subSubjectName, newSubSubjects);
    }

    @RequestMapping(path = "/admin/subject/sub_subject_delete/{id}", method = RequestMethod.GET)
    public String adminSubSubjectDelete(@PathVariable(value = "id") Integer id) {
        return adminSubSubjectService.adminSubSubjectDelete(id);
    }
}
