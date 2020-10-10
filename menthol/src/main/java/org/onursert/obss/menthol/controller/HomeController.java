package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/")
    public String home() {
        return homeService.home();
    }

    @RequestMapping(value = "/index")
    public String homeIndex() {
        return homeService.homeIndex();
    }
}
