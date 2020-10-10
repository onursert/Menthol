package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String user() {
        return userService.user();
    }

    @RequestMapping(path = "/user/index", method = RequestMethod.GET)
    public String userIndex(Model model,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "success", required = false) String success) {
        return userService.userIndex(model, error, success);
    }
}
