package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class ManualSportActivityAssembler implements SportActivityAssembler<ManualSportActivity> {

    private final UserRepository userRepository;

    @Autowired
    public ManualSportActivityAssembler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SportActivity assembleSportActivity(ManualSportActivity manualSportActivity) {
        final SportActivity sportActivity = new SportActivity()
            .setId(manualSportActivity.identifier())
            .setFirstAndLastName(retrieveFirstAndLastName(manualSportActivity.getUsername()))
            .setUsername(manualSportActivity.getUsername())
            .setTrackerSource(TrackerSource.MANUAL)
            .setSportActivityType(manualSportActivity.details().type())
            .setTitle(manualSportActivity.details().title())
            .setMovingTime(manualSportActivity.details().duration())
            .setStartDate(manualSportActivity.getStartDate());
        final String distance = manualSportActivity.details().distance();
        if(Objects.nonNull(distance)){
            sportActivity.setDistance(Float.parseFloat(distance));
            sportActivity.setUnits(manualSportActivity.details().units());
        }
        return sportActivity;
    }

    private String retrieveFirstAndLastName(String username){
        final TrackerUser user = userRepository.getUser(username);
        String firstAndLastName = user.getFirstName() + " " + user.getLastName();
        if(StringUtils.hasText(firstAndLastName)){
            return firstAndLastName;
        }
        return username;
    }
}
