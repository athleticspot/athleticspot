package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.strava.StravaSportActivity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class SportActivityAssemblerImpl {

    private final ManualSportActivityAssembler manualSportActivityAssembler;

    private final StravaActivityAssembler stravaActivityAssembler;

    public SportActivityAssemblerImpl(ManualSportActivityAssembler manualSportActivityAssembler,
                                      StravaActivityAssembler stravaActivityAssembler) {
        this.manualSportActivityAssembler = manualSportActivityAssembler;
        this.stravaActivityAssembler = stravaActivityAssembler;
    }

    public Page<SportActivity> pageAssemble(Page<SportActivityGenericType> sportActivityPage) {

        return sportActivityPage.map(sportActivityGenericType ->
            {
                if (sportActivityGenericType instanceof ManualSportActivity) {
                    return manualSportActivityAssembler
                        .assembleSportActivity((ManualSportActivity) sportActivityGenericType);
                }
                return stravaActivityAssembler.assembleSportActivity((StravaSportActivity) sportActivityGenericType);
            }
        );
    }

}
