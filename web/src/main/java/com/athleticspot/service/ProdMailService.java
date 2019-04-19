package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Profile({JHipsterConstants.SPRING_PROFILE_PRODUCTION})
public class ProdMailService implements MailService {

    private final Logger log = LoggerFactory.getLogger(ProdMailService.class);


    @Value("${mailgun.api-key}")
    private String mailgunApiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

    }

    @Override
    public void sendEmail(String toString, String subject, String contentString) {
//
//        Configuration configuration = new Configuration()
//            .domain(domain)
//            .apiKey(mailgunApiKey)
//            .from("Test account", "postmaster@somedomain.com");
//
//        Mail.using(configuration)
//            .to("marty@mcfly.com")
//            .subject("This is the subject")
//            .text("Hello world!")
//            .build()
//            .send();
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

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgunApiKey));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/" + domain
            + "/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun User <mailgun@" + domain + ">");
        formData.add("to", "tomkasp@gmail.com");
        formData.add("subject", "Simple Mailgun Example");
        formData.add("text", "Plaintext content");
        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
    }

    @Override
    public void sendSocialRegistrationValidationEmail(User user, String provider) {

    }
}
