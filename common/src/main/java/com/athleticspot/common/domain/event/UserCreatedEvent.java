package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.UserCreatedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class UserCreatedEvent extends ApplicationEvent {

    private UserCreatedEventDto content;

    public UserCreatedEvent(UserCreatedEventDto source) {
        super(source);
        this.content = source;
    }

    public UserCreatedEventDto getContent() {
        return content;
    }
}
