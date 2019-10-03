package com.athleticspot.training.domain.trainingsurvey;

import java.time.Duration;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.springframework.data.geo.Distance;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrainingHistory.class)
public abstract class TrainingHistory_ extends com.athleticspot.common.domain.model.IdentifiedDomainObject_ {

	public static volatile SingularAttribute<TrainingHistory, Duration> lastTime;
	public static volatile SingularAttribute<TrainingHistory, Distance> distance;
	public static volatile SingularAttribute<TrainingHistory, Duration> personalRecord;
	public static volatile SingularAttribute<TrainingHistory, TrainingSurveyId> trainingSurveyId;

}

