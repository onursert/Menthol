package org.onursert.obss.menthol.controller;

import org.onursert.obss.menthol.service.UserPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UserPhaseController {

    @Autowired
    private UserPhaseService userPhaseService;

    @RequestMapping(path = "/user/phase/list/{programId}", method = RequestMethod.GET)
    public String userPhaseList(Model model, @PathVariable(value = "programId") Integer programId) {
        return userPhaseService.userPhaseList(model, programId);
    }

    @RequestMapping(path = "/user/phase/edit/{phaseId}", method = RequestMethod.GET)
    public String userPhaseEdit(Model model, @PathVariable(value = "phaseId") Integer phaseId) {
        return userPhaseService.userPhaseEdit(model, phaseId);
    }

    @RequestMapping(path = "/user/phase/update", method = RequestMethod.POST)
    public String userPhaseEditUpdate(@RequestParam(value = "phaseId") Integer phaseId,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                      @RequestParam(value = "startHour") String startHour,
                                      @RequestParam(value = "startMinute") String startMinute,
                                      @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                      @RequestParam(value = "endHour") String endHour,
                                      @RequestParam(value = "endMinute") String endMinute) {
        return userPhaseService.userPhaseEditUpdate(phaseId, description, startDate, startHour, startMinute, endDate, endHour, endMinute);
    }

    @RequestMapping(path = "/user/phase/finish/{phaseId}", method = RequestMethod.GET)
    public String userPhaseFinish(Model model, @PathVariable(value = "phaseId") Integer phaseId) {
        return userPhaseService.userPhaseFinish(model, phaseId);
    }

    @RequestMapping(path = "/user/phase/finish", method = RequestMethod.POST)
    public String userPhaseFinishUpdate(@RequestParam(value = "phaseId") Integer phaseId,
                                        @RequestParam(value = "mentorComment", required = false) String mentorComment,
                                        @RequestParam(value = "mentorPoint", required = false) String mentorPoint,
                                        @RequestParam(value = "menteeComment", required = false) String menteeComment,
                                        @RequestParam(value = "menteePoint", required = false) String menteePoint) {
        return userPhaseService.userPhaseFinishUpdate(phaseId, mentorComment, mentorPoint, menteeComment, menteePoint);
    }

    @RequestMapping(path = "/user/phase/add/{programId}", method = RequestMethod.GET)
    public String userPhaseAdd(Model model, @PathVariable(value = "programId") Integer programId) {
        return userPhaseService.userPhaseAdd(model, programId);
    }

    @RequestMapping(path = "/user/phase/add", method = RequestMethod.POST)
    public String userPhaseAddUpdate(@RequestParam(value = "programId") Integer programId,
                                     @RequestParam(value = "description") String description,
                                     @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @RequestParam(value = "startHour") String startHour,
                                     @RequestParam(value = "startMinute") String startMinute,
                                     @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                     @RequestParam(value = "endHour") String endHour,
                                     @RequestParam(value = "endMinute") String endMinute) {
        return userPhaseService.userPhaseAddUpdate(programId, description, startDate, startHour, startMinute, endDate, endHour, endMinute);
    }

    @RequestMapping(path = "/user/phase/start/{phaseId}", method = RequestMethod.GET)
    public String userPhaseStart(@PathVariable(value = "phaseId") Integer phaseId) {
        return userPhaseService.userPhaseStart(phaseId);
    }
}
