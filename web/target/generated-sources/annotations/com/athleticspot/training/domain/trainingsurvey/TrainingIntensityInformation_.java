package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.training.domain.TrainingIntensity;
import java.time.DayOfWeek;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrainingIntensityInformation.class)
public abstract class TrainingIntensityInformation_ {

	public static volatile SingularAttribute<TrainingIntensityInformation, TrainingIntensity> trainingIntensity;
	public static volatile SingularAttribute<TrainingIntensityInformation, DayOfWeek> dayOfWeek;
	public static volatile SingularAttribute<TrainingIntensityInformation, Long> id;
	public static volatile SingularAttribute<TrainingIntensityInformation, Long> trainingSurveyId;

}

