package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Tomasz Kasprzycki
 */
public interface MailService {

    String USER = "user";

    String BASE_URL = "baseUrl";

    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendEmail(String toString, String subject, String contentString);

    void sendEmailFromTemplate(User user, String templateName, String titleKey);

    void sendActivationEmail(User user);

    void sendCreationEmail(User user);

    void sendPasswordResetMail(User user) throws UnirestException;

    void sendSocialRegistrationValidationEmail(User user, String provider);

}
