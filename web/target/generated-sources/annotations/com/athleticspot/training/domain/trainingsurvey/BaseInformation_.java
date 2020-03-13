package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.MetricSystemType;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseInformation.class)
public abstract class BaseInformation_ {

	public static volatile SingularAttribute<BaseInformation, LocalDate> birthDay;
	public static volatile SingularAttribute<BaseInformation, Double> bodyMass;
	public static volatile SingularAttribute<BaseInformation, Double> height;
	public static volatile SingularAttribute<BaseInformation, MetricSystemType> metricSystemType;

}

