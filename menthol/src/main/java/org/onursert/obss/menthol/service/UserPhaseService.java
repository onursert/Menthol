package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.Phase;
import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.repository.PhaseRepository;
import org.onursert.obss.menthol.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserPhaseService {

    @Autowired
    PhaseRepository phaseRepository;

    @Autowired
    ProgramRepository programRepository;

    public String userPhaseList(Model model, Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        model.addAttribute("program", program);
        model.addAttribute("phases", phaseRepository.findByProgramId(programId));
        return "user/phase/list";
    }

    public String userPhaseEdit(Model model, Integer phaseId) {
        String currentUser = getCurrentUserName();
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        Program program = phase.getProgram();
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        model.addAttribute("phase", phase);
        return "user/phase/edit";
    }

    public String userPhaseEditUpdate(Integer phaseId, String description, Date startDate, String startHour, String startMinute, Date endDate, String endHour, String endMinute) {
        if (phaseId == null || description.equals("") || startDate == null || startHour.equals("") || startMinute.equals("") || endDate == null || endHour.equals("") || endMinute.equals("")) {
            return "redirect:/user/index?error=null";
        }
        if (!startHour.chars().allMatch(Character::isDigit) || !startMinute.chars().allMatch(Character::isDigit) || !endHour.chars().allMatch(Character::isDigit) || !endMinute.chars().allMatch(Character::isDigit)) {
            return "redirect:/user/index?error=malformed_time";
        }
        if (Integer.parseInt(startHour) < 0 || Integer.parseInt(startHour) > 23 || Integer.parseInt(endHour) < 0 || Integer.parseInt(endHour) > 24 ||
                Integer.parseInt(startMinute) < 0 || Integer.parseInt(startMinute) > 59 || Integer.parseInt(endMinute) < 0 || Integer.parseInt(endMinute) > 59) {
            return "redirect:/user/index?error=malformed_time";
        }

        String currentUser = getCurrentUserName();
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        Integer startTime = (int) (startDate.getTime() / 1000) + Integer.parseInt(startHour) * 3600 + Integer.parseInt(startMinute) * 60;
        Integer endTime = (int) (endDate.getTime() / 1000) + Integer.parseInt(endHour) * 3600 + Integer.parseInt(endMinute) * 60;

        if (phase == null) {
            return "redirect:/user/index?error=null";
        }
        if (endTime < startTime) {
            return "redirect:/user/index?error=timing_error";
        }
        Program program = phase.getProgram();
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        phase.setDescription(description);
        phase.setStartDate(startTime);
        phase.setEndDate(endTime);
        phaseRepository.save(phase);
        return "redirect:/user/phase/list/" + program.getId();
    }

    public String userPhaseFinish(Model model, Integer phaseId) {
        String currentUser = getCurrentUserName();
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        Program program = phase.getProgram();
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("phase", phase);
        return "user/phase/finish";
    }

    public String userPhaseFinishUpdate(Integer phaseId, String mentorComment, String mentorPoint, String menteeComment, String menteePoint) {
        if (phaseId == null) {
            return "redirect:/user/index?error=null";
        }

        String currentUser = getCurrentUserName();
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        if (phase == null) {
            return "redirect:/user/index?error=null";
        }
        Program program = phase.getProgram();
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        if ((program.getMentorUid() != null && program.getMentorUid().equals(currentUser))) {
            phase.setMentorComment(mentorComment);
            phase.setMentorPoint(Integer.valueOf(mentorPoint));
        }

        if ((program.getMenteeUid() != null && program.getMenteeUid().equals(currentUser))) {
            phase.setMenteeComment(menteeComment);
            phase.setMenteePoint(Integer.valueOf(menteePoint));
        }

        phase.setEndDate((int) (Instant.now().getEpochSecond()));
        phase.setStarted(Boolean.TRUE);
        phase.setEnded(Boolean.TRUE);
        phaseRepository.save(phase);
        return "redirect:/user/phase/list/" + program.getId();
    }

    public String userPhaseAdd(Model model, Integer programId) {
        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        model.addAttribute("program", program);
        return "user/phase/add";
    }

    public String userPhaseAddUpdate(Integer programId, String description, Date startDate, String startHour, String startMinute, Date endDate, String endHour, String endMinute) {
        if (programId == null || description.equals("") || startDate == null || startHour.equals("") || startMinute.equals("") || endDate == null || endHour.equals("") || endMinute.equals("")) {
            return "redirect:/user/index?error=null";
        }
        if (!startHour.chars().allMatch(Character::isDigit) || !startMinute.chars().allMatch(Character::isDigit) || !endHour.chars().allMatch(Character::isDigit) || !endMinute.chars().allMatch(Character::isDigit)) {
            return "redirect:/user/index?error=malformed_time";
        }
        if (Integer.parseInt(startHour) < 0 || Integer.parseInt(startHour) > 23 || Integer.parseInt(endHour) < 0 || Integer.parseInt(endHour) > 24 ||
                Integer.parseInt(startMinute) < 0 || Integer.parseInt(startMinute) > 59 || Integer.parseInt(endMinute) < 0 || Integer.parseInt(endMinute) > 59) {
            return "redirect:/user/index?error=malformed_time";
        }

        String currentUser = getCurrentUserName();
        Program program = programRepository.findById(programId).orElse(null);
        Integer startTime = (int) (startDate.getTime() / 1000) + Integer.parseInt(startHour) * 3600 + Integer.parseInt(startMinute) * 60;
        Integer endTime = (int) (endDate.getTime() / 1000) + Integer.parseInt(endHour) * 3600 + Integer.parseInt(endMinute) * 60;

        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (endTime < startTime) {
            return "redirect:/user/index?error=timing_error";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        Phase phase = new Phase();
        phase.setProgram(program);
        phase.setDescription(description);
        phase.setStartDate(startTime);
        phase.setEndDate(endTime);

        phase.setEnded(Boolean.FALSE);
        phase.setStarted(Boolean.FALSE);
        phaseRepository.save(phase);
        return "redirect:/user/phase/list/" + program.getId();
    }

    public String userPhaseStart(Integer phaseId) {
        if (phaseId == null) {
            return "redirect:/user/index?error=null";
        }
        String currentUser = getCurrentUserName();
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        if (phase == null) {
            return "redirect:/user/index?error=null";
        }
        Program program = phase.getProgram();
        if (program == null) {
            return "redirect:/user/index?error=null";
        }
        if (
                (program.getMentorUid() != null && !program.getMentorUid().equals(currentUser))
                        &&
                        (program.getMenteeUid() != null && !program.getMenteeUid().equals(currentUser))
        ) {
            return "redirect:/user/index?error=not_auth";
        }
        if (!program.getAccepted()) {
            return "redirect:/user/index?error=not_auth";
        }
        if (program.getStartDate() == null) {
            return "redirect:/user/index?error=not_start";
        }

        List<Phase> oldPhaseList = program.getPhases();
        for (Phase oldPhase : oldPhaseList) {
            if (oldPhase != phase && oldPhase.getStarted()) {
                oldPhase.setEnded(Boolean.TRUE);
                phaseRepository.save(oldPhase);
            }
        }

        phase.setStarted(Boolean.TRUE);
        phase.setEnded(Boolean.FALSE);
        phaseRepository.save(phase);
        return "redirect:/user/phase/list/" + program.getId();
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
