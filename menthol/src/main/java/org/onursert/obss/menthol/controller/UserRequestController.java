package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRequestController {

    @Autowired
    UserRequestService userRequestService;

    @RequestMapping(path = "/user/request/accept/{programId}", method = RequestMethod.GET)
    public String userRequestAccept(@PathVariable(value = "programId") Integer programId) {
        return userRequestService.userRequestAccept(programId);
    }

    @RequestMapping(path = "/user/request/decline/{programId}", method = RequestMethod.GET)
    public String userRequestDecline(@PathVariable(value = "programId") Integer programId) {
        return userRequestService.userRequestDecline(programId);
    }

    @RequestMapping(path = "/user/request/program_end/{programId}", method = RequestMethod.GET)
    public String userRequestProgramEnd(@PathVariable(value = "programId") Integer programId) {
        return userRequestService.userRequestProgramEnd(programId);
    }
}
