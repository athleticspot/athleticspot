package com.athleticspot.service;

import com.athleticspot.domain.User;

/**
 * @author Tomasz Kasprzycki
 */
public interface MailService {

    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendEmail(String toString, String subject, String contentString);

    void sendEmailFromTemplate(User user, String templateName, String titleKey);

    void sendActivationEmail(User user);

    void sendCreationEmail(User user);

    void sendPasswordResetMail(User user);

    void sendSocialRegistrationValidationEmail(User user, String provider);

}
