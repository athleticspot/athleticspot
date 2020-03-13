package com.athleticspot.tracker.domain.model;

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
    SWIM("SWIM"),
    /**
     * Hike
     */
    HIKE("HIKE"),
    /**
     * Walk
     */
    WALK("WALK"),


    /**
     * Alpine skiing
     */
    ALPINE_SKI("ALPINE_SKI"),
    /**
     * Back-country skiing (off piste)
     */
    BACKCOUNTRY_SKI("BACKCOUNTRY_SKI"),
    /**
     * Canoeing
     */
    CANOEING("CANOEING"),
    /**
     * Crossfit
     */
    CROSSFIT("CROSSFIT"),
    /**
     * E-Bike Ride
     */
    EBIKE_RIDE("EBIKE_RIDE"),
    /**
     * Elliptical Trainer
     */
    ELLIPTICAL("ELLIPTICAL"),
    /**
     * Ice skating
     */
    ICE_SKATE("ICE_SKATE"),
    /**
     * Inline skating (rollerblading)
     */
    INLINE_SKATE("INLINE_SKATE"),
    /**
     * Kayaking
     */
    KAYAKING("KAYAKING"),
    /**
     * Kite surfing
     */
    KITESURF("KITESURF"),
    /**
     * Nordic skiing (telemark)
     */
    NORDIC_SKI("NORDIC_SKI"),
    /**
     * Rock climbing
     */
    ROCK_CLIMBING("ROCK_CLIMBING"),
    /**
     * Rollerskiing
     */
    ROLLERSKI("ROLLERSKI"),
    /**
     * Rowing
     */
    ROWING("ROWING"),
    /**
     * Snowboarding
     */
    SNOWBOARD("SNOWBOARD"),
    /**
     * Snowshoeing
     */
    SNOWSHOE("SNOWSHOE"),
    /**
     * Stair stepper
     */
    STAIR_STEPPER("STAIR_STEPPER"),
    /**
     * Stand-up Paddling
     */
    STAND_UP_PADDLING("STAND_UP_PADDLING"),
    /**
     * Surfing
     */
    SURFING("SURFING"),
    /**
     * Virtual ride
     */
    VIRTUAL_RIDE("VIRTUAL_RIDE"),
    /**
     * Weight training
     */
    WEIGHT_TRAINING("WEIGHT_TRAINING"),
    /**
     * Windsurfing
     */
    WINDSURF("WINDSURF"),
    /**
     * Workout
     */
    WORKOUT("WORKOUT"),
    /**
     * Yoga
     */
    YOGA("YOGA"),
    /**
     */
    UNKNOWN("UNKNOWN");


    private final String type;

    SportActivityType(String type) {
        this.type = type;
    }
}
