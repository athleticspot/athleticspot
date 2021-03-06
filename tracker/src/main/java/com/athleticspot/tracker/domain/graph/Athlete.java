package com.athleticspot.tracker.domain.graph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.util.Assert;

import javax.persistence.Column;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@NodeEntity
public class Athlete {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String athleteUUID;

    /**
     * User login
     */
    @Column(unique = true)
    private String name;

    private String firstAndLastName;

    private Athlete(){
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Athlete(String name, String athleteUuid, String firstAndLastName) {
        Assert.notNull(name, "Athlete name cannot be null");
        Assert.notNull(athleteUuid, "Athlete UUID cannot be null");
        this.name = name;
        this.athleteUUID = athleteUuid;
        this.firstAndLastName = firstAndLastName;
    }

    public Athlete(String name) {
        this.name = name;
    }

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "FALLOW", direction = Relationship.OUTGOING)
    private Set<Athlete> friends;

    @Relationship(type = "PERFORM", direction = Relationship.UNDIRECTED)
    private Set<SportActivity> sportActivities;


    public void fallow(Athlete person) {
        if (friends == null) {
            friends = new HashSet<>();
        }
        friends.add(person);
    }

    public void perform(SportActivity sportActivity){
        if(sportActivities == null){
            sportActivities = new HashSet<>();
        }
        sportActivities.add(sportActivity);
    }


    public void perform(List<SportActivity> sportActivities) {
        if(this.sportActivities == null){
            this.sportActivities = new HashSet<>();
        }
        this.sportActivities.addAll(sportActivities);
    }

    public void unfallow(Long unfallowAthleteId) {
        this.friends.removeIf(athlete ->
                athlete.id.equals(unfallowAthleteId)
        );
    }

    public void fetchAllFriends() {

    }

    public void fetchAllFriendsPaged() {

    }

    public void fetchMyActivities() {

    }

    public Set<SportActivity> fetchAllActivities() {
        return this.sportActivities;
    }

    public Optional<SportActivity> fetchActivity(Long sportActivityId) {
        return this.sportActivities.stream()
            .filter(sportActivity -> sportActivity.getId().equals(sportActivityId))
            .findFirst();
    }

    public void fetchAllActivitiesPaged() {

    }


    public String toString() {
        return this.name + "'s friends => "
                + Optional.ofNullable(this.friends).orElse(
                Collections.emptySet()).stream()
                .map(Athlete::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Athlete)) return false;
        Athlete athlete = (Athlete) o;
        return athleteUUID.equals(athlete.athleteUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(athleteUUID);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getAthleteUUID() {
        return athleteUUID;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public Set<Athlete> getFriends() {
        return friends;
    }

    public Set<SportActivity> getSportActivities() {
        return sportActivities;
    }

    public void updateFirstAndLastName(String firstName, String lastName) {
        this.firstAndLastName = firstName + " " + lastName;
    }

    public List<Long> checkIfFollow(List<Long> followedAthleteId) {
        return followedAthleteId.stream()
            .filter(aLong -> this.friends.stream().anyMatch(athlete -> athlete.getId().equals(aLong)))
            .collect(Collectors.toList());
    }

    public boolean checkIfFollowUser(Long followedUserId){
        Assert.notNull(followedUserId, "followedUserId cannot be null");
        if(Objects.isNull(this.friends)) return false;
        return this.friends.stream()
            .anyMatch(athlete -> followedUserId.equals(athlete.getId()));
    }
}
