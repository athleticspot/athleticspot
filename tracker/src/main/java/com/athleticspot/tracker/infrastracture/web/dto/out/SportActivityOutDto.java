package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityOutDto {

    private String sportyActivityIdentifier;
    private SportActivityDetailsOutDto details;
    private String source;

    private SportActivityOutDto(ManualSportActivity manualSportActivity) {
        this.sportyActivityIdentifier = manualSportActivity.identifier();
        this.details = SportActivityDetailsOutDto.create(manualSportActivity.details());
        this.source = manualSportActivity.source();
    }

    public static SportActivityOutDto create(ManualSportActivity manualSportActivity) {

        return new SportActivityOutDto(manualSportActivity);
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
