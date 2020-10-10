package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;

    @RequestMapping(path = "/user/search/list", method = RequestMethod.POST)
    public String userSearchList(Model model, @RequestParam(value = "searchFor") String searchFor) {
        return userSearchService.userSearchList(model, searchFor);
    }

    @RequestMapping(path = "/user/search/subject/{subjectId}", method = RequestMethod.GET)
    public String userSearchSubject(Model model, @PathVariable(value = "subjectId") Integer subjectId) {
        return userSearchService.userSearchSubject(model, subjectId);
    }

    @RequestMapping(path = "/user/search/subSubject/{subSubjectId}", method = RequestMethod.GET)
    public String userSearchSubSubject(Model model, @PathVariable(value = "subSubjectId") Integer subSubjectId) {
        return userSearchService.userSearchSubSubject(model, subSubjectId);
    }

    @RequestMapping(path = "/user/search/join/{programId}", method = RequestMethod.GET)
    public String userSearchJoin(@PathVariable(value = "programId") Integer programId) {
        return userSearchService.userSearchJoin(programId);
    }
}
