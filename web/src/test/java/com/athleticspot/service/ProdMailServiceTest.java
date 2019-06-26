package com.athleticspot.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki
 */
public class ProdMailServiceTest {

    @Test
    @Ignore
    public void sendPasswordResetMail() throws UnirestException {

        ProdMailService prodMailService = new ProdMailService();

        prodMailService.sendPasswordResetMail(null);

        Assertions.assertThat(true).isTrue();
    }
}
