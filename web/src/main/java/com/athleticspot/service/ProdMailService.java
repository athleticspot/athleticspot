package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.jhipster.config.JHipsterConstants;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Profile({JHipsterConstants.SPRING_PROFILE_PRODUCTION})
public class ProdMailService implements MailService {

    private final Logger log = LoggerFactory.getLogger(ProdMailService.class);


    @Value("${mailgun.api-key}")
    String mailgunApiKey;

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
    public void sendPasswordResetMail(User user) throws UnirestException {
        final HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
            .basicAuth("api", mailgunApiKey)
            .queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
            .queryString("to", "tomkasp@gmail.com")
            .queryString("subject", "hello")
            .queryString("text", "testing")
            .asJson();
        log.info("Reset email body {}", request.getBody());
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
