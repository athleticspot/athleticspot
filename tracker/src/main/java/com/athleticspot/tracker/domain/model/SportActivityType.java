package com.athleticspot.tracker.domain.model;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ActivityTypeSerializer;

/**
 * @author Tomasz Kasprzycki
 */
public enum SportActivityType {

    /**
     * Bike ride
     */
    RIDE("RIDE"),  
    /**
     * Run
     */
    RUN("RUN"),
    /**
     * Swim
     */
    SWIM("SWIM");
    /**
     * Hike
     */
//    HIKE(),
//    /**
//     * Walk
//     */
//    WALK(),
//    /**
//     * Alpine skiing
//     */
//    ALPINE_SKI(),
//    /**
//     * Back-country skiing (off piste)
//     */
//    BACKCOUNTRY_SKI(),
//    /**
//     * Canoeing
//     */
//    CANOEING(),
//    /**
//     * Crossfit
//     */
//    CROSSFIT(),
//    /**
//     * E-Bike Ride
//     */
//    EBIKE_RIDE(),
//    /**
//     * Elliptical Trainer
//     */
//    ELLIPTICAL(),
//    /**
//     * Ice skating
//     */
//    ICE_SKATE(),
//    /**
//     * Inline skating (rollerblading)
//     */
//    INLINE_SKATE(),
//    /**
//     * Kayaking
//     */
//    KAYAKING(),
//    /**
//     * Kite surfing
//     */
//    KITESURF(),
//    /**
//     * Nordic skiing (telemark)
//     */
//    NORDIC_SKI(),
//    /**
//     * Rock climbing
//     */
//    ROCK_CLIMBING(),
//    /**
//     * Rollerskiing
//     */
//    ROLLERSKI(),
//    /**
//     * Rowing
//     */
//    ROWING(),
//    /**
//     * Snowboarding
//     */
//    SNOWBOARD(),
//    /**
//     * Snowshoeing
//     */
//    SNOWSHOE(),
//    /**
//     * Stair stepper
//     */
//    STAIR_STEPPER(),
//    /**
//     * Stand-up Paddling
//     */
//    STAND_UP_PADDLING(),
//    /**
//     * Surfing
//     */
//    SURFING(),
//    /**
//     * Virtual ride
//     */
//    VIRTUAL_RIDE(),
//    /**
//     * Weight training
//     */
//    WEIGHT_TRAINING(),
//    /**
//     * Windsurfing
//     */
//    WINDSURF(),
//    /**
//     * Workout
//     */
//    WORKOUT(),
//    /**
//     * Yoga
//     */
//    YOGA(),
//    /**
//     * <p>
//     * Should never occur but may if Strava API behaviour has changed
//     * </p>
//     */
//    UNKNOWN();


    private final String type;

    SportActivityType(String type) {
        this.type = type;
    }
}
