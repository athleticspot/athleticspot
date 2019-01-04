package com.athleticspot.common.domain.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteCreatedEvent extends ApplicationEvent {

    public AthleteCreatedEvent(Object source) {
        super(source);
    }
}
