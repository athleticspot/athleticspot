package com.athleticspot.tracker.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TrackerInfo {

    private boolean connectedToStrava;

    public TrackerInfo(boolean connectedToStrava) {
        this.connectedToStrava = connectedToStrava;
    }
}
