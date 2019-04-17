package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.sendgrid.*;
import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Profile({JHipsterConstants.SPRING_PROFILE_PRODUCTION})
public class ProdMailService implements MailService{

    private final Logger log = LoggerFactory.getLogger(ProdMailService.class);

    private final SendGrid sendGrid;

    @Autowired
    public ProdMailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

    }

    @Override
    public void sendEmail(String toString, String subject, String contentString) {
        log.info("Reseting password");
        Email from = new Email("test@example.com");
        Email to = new Email(toString);
        Content content = new Content("text/plain", contentString);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            sendGrid.api(request);

        } catch (IOException ex) {
            log.error("Error occured during email send", ex);
        }

    }

    @Override
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {

    }

    @Override
    public void sendActivationEmail(User user) {

    }

    @Override
    public void sendCreationEmail(User user) {

    }

    @Override
    public void sendPasswordResetMail(User user) {

        Email from = new Email("test@example.com");
        Email to = new Email("tomkasp@gmail.com");
        Content content = new Content("text/plain", "reset");
        Mail mail = new Mail(from, "account reset", to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            sendGrid.api(request);

        } catch (IOException ex) {
            log.error("Error occured during email send", ex);
        }
    }

    @Override
    public void sendSocialRegistrationValidationEmail(User user, String provider) {

    }
}
