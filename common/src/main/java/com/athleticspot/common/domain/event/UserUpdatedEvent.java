package com.athleticspot.common.domain.event;

import com.athleticspot.common.infrastracture.dto.UserUpdatedEventDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class UserUpdatedEvent extends ApplicationEvent {

    private UserUpdatedEventDto content;

    public UserUpdatedEvent(UserUpdatedEventDto userUpdatedEventDto) {
        super(userUpdatedEventDto);
        this.content = userUpdatedEventDto;
    }

    public UserUpdatedEventDto getContent() {
        return content;
    }
}
