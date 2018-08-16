package com.athleticspot.tracker.domain.model;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "jhi_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "timeline_seq", allocationSize = 1)
public class TrackerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timeline_id")
    private String timelineIdentifier;



}


