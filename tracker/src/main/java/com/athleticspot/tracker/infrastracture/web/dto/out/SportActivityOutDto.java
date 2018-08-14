package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityDetails;

import javax.persistence.Column;
import javax.persistence.Embedded;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityOutDto {

    private String sportyActivityIdentifier;
    private SportActivityDetailsOutDto details;
    private String source;

    private SportActivityOutDto(SportActivity sportActivity) {
        this.sportyActivityIdentifier = sportActivity.identifier();
        this.details = SportActivityDetailsOutDto.create(sportActivity.details());
        this.source = sportActivity.source();
    }

    public static SportActivityOutDto create(SportActivity sportActivity) {

        return new SportActivityOutDto(sportActivity);
    }

    public String getSportyActivityIdentifier() {
        return sportyActivityIdentifier;
    }

    public SportActivityDetailsOutDto getDetails() {
        return details;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "SportActivityOutDto{" +
            "sportyActivityIdentifier='" + sportyActivityIdentifier + '\'' +
            ", details=" + details +
            ", source='" + source + '\'' +
            '}';
    }
}
