package com.athleticspot.training.domain;

import com.athleticspot.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Athlete.class)
public abstract class Athlete_ extends com.athleticspot.common.domain.model.IdentifiedDomainObject_ {

	public static volatile SingularAttribute<Athlete, String> name;
	public static volatile SingularAttribute<Athlete, User> user;

}

