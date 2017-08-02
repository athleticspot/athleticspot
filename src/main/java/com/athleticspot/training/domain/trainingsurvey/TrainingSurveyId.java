package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.AbstractId;

import javax.persistence.Embeddable;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class TrainingSurveyId extends AbstractId {

    private static final long serialVersionUID = 1L;

    protected TrainingSurveyId() {
        super();
    }

    public TrainingSurveyId(String uuid) {
        super(uuid);
    }

    @Override
    protected int hashOddValue() {
        return 83811;
    }

    @Override
    protected int hashPrimeValue() {
        return 263;
    }

    @Override
    protected void validateId(String anId) {
        try {
            UUID.fromString(anId);
        } catch (Exception e) {
            throw new IllegalArgumentException("The id has an invalid format.");
        }
    }
}
