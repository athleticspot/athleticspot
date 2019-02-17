package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.AthleteUpdatedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteUpdatedEvent extends ApplicationEvent {

    private AthleteUpdatedEventDto content;

    public AthleteUpdatedEvent(AthleteUpdatedEventDto athleteUpdatedEventDto) {
        super(athleteUpdatedEventDto);
        this.content = athleteUpdatedEventDto;
    }

    public AthleteUpdatedEventDto getContent() {
        return content;
    }
}
