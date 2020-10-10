package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserMoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserMoreController {

    @Autowired
    private UserMoreService userMoreService;

    @RequestMapping(path = "/user/more", method = RequestMethod.GET)
    public String userMore() {
        return userMoreService.userMore();
    }

    @RequestMapping(path = "/user/more/mentor", method = RequestMethod.GET)
    public String userMoreMentor(Model model) {
        return userMoreService.userMoreMentor(model);
    }

    @RequestMapping(path = "/user/more/mentee", method = RequestMethod.GET)
    public String userMoreMentee(Model model) {
        return userMoreService.userMoreMentee(model);
    }

    @RequestMapping(path = "/user/more/program", method = RequestMethod.GET)
    public String userMoreProgram(Model model) {
        return userMoreService.userMoreProgram(model);
    }

    @RequestMapping(path = "/user/more/application", method = RequestMethod.GET)
    public String userMoreApplication(Model model) {
        return userMoreService.userMoreApplication(model);
    }
}
