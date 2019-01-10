package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.AthleteCreatedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteCreatedEvent extends ApplicationEvent {

    public AthleteCreatedEvent(AthleteCreatedEventDto source) {
        super(source);
    }
}
