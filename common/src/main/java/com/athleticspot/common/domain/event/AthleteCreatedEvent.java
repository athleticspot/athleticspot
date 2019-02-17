package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.AthleteCreatedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteCreatedEvent extends ApplicationEvent {

    private AthleteCreatedEventDto content;

    public AthleteCreatedEvent(AthleteCreatedEventDto source) {
        super(source);
        this.content = source;
    }

    public AthleteCreatedEventDto getContent() {
        return content;
    }
}
