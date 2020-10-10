package org.onursert.obss.menthol.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailService {

    private static TemplateEngine templateEngine;
    private static Context context;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setEmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void prepareAndSendMail(String mailTo, String mailBody) throws MessagingException {
        String htmlTemplate = "templates/email/template";
        initializeTemplateEngine();

        context.setVariable("mailSubject", mailBody);

        String htmlBody = templateEngine.process(htmlTemplate, context);
        sendEmail(mailTo, htmlBody);
    }

    private static void initializeTemplateEngine() {
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setTemplateMode("HTML");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setCharacterEncoding("UTF-8");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(classLoaderTemplateResolver);
        context = new Context(Locale.US);
    }

    private void sendEmail(String mailTo, String mailBody) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("<obss_noreply@onursert.org>");
        mimeMessageHelper.setTo("<" + mailTo + ">");
        mimeMessageHelper.setSubject("Phase Reminder from Menthol");
        mimeMessageHelper.setText(mailBody, true);
        javaMailSender.send(mimeMessage);
    }
}
