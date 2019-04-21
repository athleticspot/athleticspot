package com.athleticspot.service;

import com.athleticspot.domain.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.jhipster.config.JHipsterConstants;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Profile({JHipsterConstants.SPRING_PROFILE_PRODUCTION})
public class ProdMailService implements MailService {

    private final Logger log = LoggerFactory.getLogger(ProdMailService.class);


    @Value("${mailgun.api-key}")

    @Value("${mailgun.domain}")
    private String domain = "sandboxda0af86ffc8f475d88e5d66716051833.mailgun.org";

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

        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
            .setSSLSocketFactory(sslsf)
            .build();

        final HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
            .basicAuth("api", mailgunApiKey)
            .queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
            .queryString("to", "artemis@example.com")
            .queryString("subject", "hello")
            .queryString("text", "testing")
            .asJson();
        request.getBody();

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

//
//    public static ClientConfig configureClient() {
//        TrustManager[] certs = new TrustManager[]{
//            new X509TrustManager() {
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain, String authType)
//                    throws CertificateException {
//                }
//
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain, String authType)
//                    throws CertificateException {
//                }
//            }
//        };
//        SSLContext ctx = null;
//        try {
//            ctx = SSLContext.getInstance("TLS");
//            ctx.init(null, certs, new SecureRandom());
//        } catch (java.security.GeneralSecurityException ex) {
//        }
//        HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
//
//        ClientConfig config = new DefaultClientConfig();
//        try {
//            config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(
//                new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                },
//                ctx
//            ));
//        } catch (Exception e) {
//        }
//        return config;
//    }
}
