package com.athleticspot.tracker.application;

import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaDataFactory {

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    private final int clientCode = 14842;

    public String clientSecret() {
        return clientSecret;
    }

    public int clientCode() {
        return clientCode;
    }
}
