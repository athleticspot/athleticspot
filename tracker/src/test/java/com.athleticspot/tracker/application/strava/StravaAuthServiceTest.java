package com.athleticspot.tracker.application.strava;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaAuthServiceTest {

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    public final String code = "e415c66183b8cc6bab8e06c55b38321fa632c008";

    @Test
    public void getTokenTest(){


        StravaAuthService test = new StravaAuthService();

        test.authenticateTracker(clientSecret, code);

        Assertions.assertThat(true).isTrue();
    }

}
