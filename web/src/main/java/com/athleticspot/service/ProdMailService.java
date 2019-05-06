package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.jhipster.config.JHipsterProperties;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.Locale;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ProdMailService implements MailService {

    private final Logger log = LoggerFactory.getLogger(ProdMailService.class);

    @Value("${mailgun.api-key}")
    String mailgunApiKey;

    @Value("${mailgun.domain}")
    private String domain;

    private final SpringTemplateEngine templateEngine;

    private final MessageSource messageSource;

    private final JHipsterProperties jHipsterProperties;


    @Autowired
    public ProdMailService(SpringTemplateEngine templateEngine,
                           MessageSource messageSource,
                           JHipsterProperties jHipsterProperties) {
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
        this.jHipsterProperties = jHipsterProperties;
    }


    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        String type = isHtml ? "html" : "text";
        try {
            Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
                .basicAuth("api", mailgunApiKey)
                .queryString("from", "Athleticspot <athleticspot@mail.athleticspot.com>")
                .queryString("to", to)
                .queryString("subject", subject)
                .queryString(type, content)
                .asJson();
        } catch (UnirestException e) {
            log.error("Error during email send", e);
        }
    }

    @Override
    public void sendEmail(String toString, String subject, String contentString) {

    }

    @Override
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Override
    public void sendActivationEmail(User user) {

    }

    @Override
    public void sendCreationEmail(User user) {

    }

    @Async
    @Override
    public void sendPasswordResetMail(User user) throws UnirestException {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "passwordResetEmail", "email.reset.title");
    }

    static {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }

            }};
            SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            Unirest.setHttpClient(httpclient);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSocialRegistrationValidationEmail(User user, String provider) {

    }

}
