package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.AthleteDeletedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteDeletedEvent extends ApplicationEvent {

    private AthleteDeletedEventDto content;

    public AthleteDeletedEvent(AthleteDeletedEventDto source) {
        super(source);
        this.content = source;
    }

    public AthleteDeletedEventDto getContent() {
        return content;
    }
}
