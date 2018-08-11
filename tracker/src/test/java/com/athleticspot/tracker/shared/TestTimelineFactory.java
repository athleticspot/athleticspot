package com.athleticspot.tracker.shared;

import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.Timeline;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TestTimelineFactory {

    public static Timeline testTimeline(String timelineIdentifier) {
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());
        return Timeline.create(timelineIdentifier);
    }

}
