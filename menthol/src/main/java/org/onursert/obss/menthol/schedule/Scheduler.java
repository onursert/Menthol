package org.onursert.obss.menthol.schedule;

import org.onursert.obss.menthol.database.repository.PersonRepository;
import org.onursert.obss.menthol.model.Phase;
import org.onursert.obss.menthol.model.Program;
import org.onursert.obss.menthol.repository.PhaseRepository;
import org.onursert.obss.menthol.schedule.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.List;

@Configuration
@EnableScheduling
public class Scheduler {

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailService emailService;

    // Every 5 min with 1 min delay
    @Scheduled(initialDelay = 300000, fixedDelay = 60000)
    public void startScheduler() throws MessagingException {
        List<Phase> allPhases = phaseRepository.findAll();
        for (Phase phase : allPhases) {
            if (phase.getEndDate() != null) {
                if (phase.getEndDate() - (int) (Instant.now().getEpochSecond()) <= 3600000 &&
                        phase.getEndDate() - (int) (Instant.now().getEpochSecond()) > 0) {
                    Program program = phase.getProgram();
                    String mentorEmail = personRepository.getPersonNamesByUid(program.getMentorUid()).getDescription();
                    String menteeEmail = personRepository.getPersonNamesByUid(program.getMenteeUid()).getDescription();

                    StringBuilder mailBody = new StringBuilder();
                    mailBody.append("Your phase with id ");
                    mailBody.append(phase.getId());
                    mailBody.append(" in program with id ");
                    mailBody.append(program.getId());
                    mailBody.append(", mentor with id ");
                    mailBody.append(program.getMentorUid());
                    mailBody.append(", mentee with id ");
                    mailBody.append(program.getMenteeUid());
                    mailBody.append(" will be expire in 1 hour.");

                    emailService.prepareAndSendMail(mentorEmail, mailBody.toString());
                    emailService.prepareAndSendMail(menteeEmail, mailBody.toString());
                }
            }
        }
    }
}
