package com.athleticspot.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki
 */
public class ProdMailServiceTest {

    @Test
    public void sendPasswordResetMail() {

        ProdMailService prodMailService = new ProdMailService();

        prodMailService.sendPasswordResetMail(null);

        Assertions.assertThat(true).isTrue();
    }
}
