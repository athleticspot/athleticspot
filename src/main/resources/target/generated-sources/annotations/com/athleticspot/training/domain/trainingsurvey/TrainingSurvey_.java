package com.athleticspot.training.domain.trainingsurvey;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrainingSurvey.class)
public abstract class TrainingSurvey_ extends com.athleticspot.common.domain.model.IdentifiedDomainObject_ {

	public static volatile SingularAttribute<TrainingSurvey, NutritionInformation> nutritionInformation;
	public static volatile SingularAttribute<TrainingSurvey, BaseInformation> baseInformation;
	public static volatile SingularAttribute<TrainingSurvey, HealthInformation> healthInformation;
	public static volatile SingularAttribute<TrainingSurvey, TrainingGoal> runningTrainingGoal;
	public static volatile SingularAttribute<TrainingSurvey, TrainingSurveyId> trainingSurveyId;

}

