package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.strava.StravaSportActivity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class SportActivityAssemblerImpl {

    private final ManualSportActivityAssembler manualSportActivityAssembler;

    private final StravaActivityAssembler stravaActivityAssembler;

    private final UserRepository userRepository;


    public SportActivityAssemblerImpl(ManualSportActivityAssembler manualSportActivityAssembler,
                                      StravaActivityAssembler stravaActivityAssembler,
                                      UserRepository userRepository) {
        this.manualSportActivityAssembler = manualSportActivityAssembler;
        this.stravaActivityAssembler = stravaActivityAssembler;
        this.userRepository = userRepository;
    }

    public Page<SportActivity> pageAssemble(Page<SportActivityGenericType> sportActivityPage) {

        return sportActivityPage.map(sportActivityGenericType ->
            assembleSportActivity(sportActivityGenericType)
        );
    }

    public SportActivity assembleSportActivity(SportActivityGenericType sportActivityGenericType) {
        SportActivity sportActivity;
        if (sportActivityGenericType instanceof ManualSportActivity) {
            sportActivity = manualSportActivityAssembler
                .assembleSportActivity((ManualSportActivity) sportActivityGenericType);
        } else {
            sportActivity = stravaActivityAssembler.assembleSportActivity((StravaSportActivity) sportActivityGenericType);
        }
        sportActivity.setFirstAndLastName(retrieveFirstAndLastName(sportActivityGenericType.getUsername()));
        return sportActivity;
    }

    private String retrieveFirstAndLastName(String username) {
        final TrackerUser user = userRepository.getUser(username);
        String firstAndLastName = user.getFirstName() + " " + user.getLastName();
        if (StringUtils.hasText(firstAndLastName)) {
            return firstAndLastName;
        }
        return username;
    }

}
