package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.SportActivityBuilder;
import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.dto.out.SportActivityOutDto;
import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;
import javastrava.model.reference.StravaActivityType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class SportActivityAssemblerImplTest {

    private static final Long STRAVA_ID = 123L;
    private static final Long SPORT_ACTIVITY_ID = 1L;
    private static final String USER_NAME = "tom kasp";
    private final String userUuid = UUID.randomUUID().toString();

    @Mock
    private MeasureSystemProvider measureSystemProvider;

    @Test
    public void when_strava_sport_activity_is_assembled_then_strava_activity_url_is_generated() {
        //given:
        Mockito.when(measureSystemProvider.getUserDistanceUnit()).thenReturn(MetricPrefix.KILO(Units.METRE));
        SportActivityAssemblerImpl sportActivityAssembler = new SportActivityAssemblerImpl(measureSystemProvider);
        final SportActivityOutDto sportActivityDto = createSportActivityDto();

        //when:
        final Page<SportActivityOutDto> sportActivityOutDtos = sportActivityAssembler.pageAssemble(createSportActivityPage());

        //then:
        Assertions.assertThat(sportActivityOutDtos.getContent()).containsExactly(sportActivityDto);
    }

    private SportActivityOutDto createSportActivityDto() {
        return new SportActivityOutDto()
            .setId(SPORT_ACTIVITY_ID.toString())
            .setExternalId(STRAVA_ID.toString())
            .setUserUuid(userUuid)
            .setUsername(USER_NAME)
            .setTrackerSource(TrackerSource.STRAVA)
            .setDistance(0F)
            .setDistanceUnits("km")
            .setElapsedTime("0h 0m 0s")
            .setElapsedTimeInSeconds(0)
            .setPace()
            .setCoordinates(Lists.newArrayList())
            .setActivityUrl("https://www.strava.com/activities/" + STRAVA_ID)
            .setSportActivityType(SportActivityType.BACKCOUNTRY_SKI);
    }

    private Page<SportActivity> createSportActivityPage() {
        final StravaActivity stravaActivity = new StravaActivity();
        stravaActivity.setId(STRAVA_ID);
        stravaActivity.setType(StravaActivityType.BACKCOUNTRY_SKI);
        final SportActivity sportActivity = SportActivityBuilder.createFromStravaActivity(
            stravaActivity,
            userUuid,
            USER_NAME
        ).createSportActivity();
        ReflectionTestUtils.setField(sportActivity, "id", SPORT_ACTIVITY_ID);
        return new PageImpl<>(Lists.newArrayList(sportActivity));
    }
}
