package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserMentorApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserMentorApplicationController {

    @Autowired
    private UserMentorApplicationService userMentorApplicationService;

    @RequestMapping(path = "/user/mentor", method = RequestMethod.GET)
    public String userMentorApplication() {
        return userMentorApplicationService.userMentorApplication();
    }

    @RequestMapping(path = "/user/mentor/application", method = RequestMethod.GET)
    public String userMentorApplicationGet(Model model) {
        return userMentorApplicationService.userMentorApplicationGet(model);
    }

    @RequestMapping(path = "/user/mentor/application", method = RequestMethod.POST)
    public String userMentorApplicationPost(@RequestParam(value = "description") String description,
                                            @RequestParam(value = "subjectIdName") String subjectIdName
    ) {
        return userMentorApplicationService.userMentorApplicationPost(description, subjectIdName);
    }

    @RequestMapping(path = "/user/mentor/application2", method = RequestMethod.GET)
    public String userMentorApplication2Get(Model model,
                                            @RequestParam(value = "description") String description,
                                            @RequestParam(value = "subjectId") String subjectId
    ) {
        return userMentorApplicationService.userMentorApplication2Get(model, description, subjectId);
    }

    @RequestMapping(path = "/user/mentor/application2", method = RequestMethod.POST)
    public String userMentorApplication2Post(@RequestParam(value = "description") String description,
                                             @RequestParam(value = "subjectId") String subjectId,
                                             @RequestParam(value = "subSubjects", defaultValue = "") String subSubjects
    ) {
        return userMentorApplicationService.userMentorApplication2Post(description, subjectId, subSubjects);
    }
}
