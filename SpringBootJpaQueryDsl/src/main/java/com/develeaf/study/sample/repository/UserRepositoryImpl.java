package com.develeaf.study.sample.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.develeaf.study.sample.entity.QUserEntity;
import com.develeaf.study.sample.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

	@Autowired
	private EntityManager em;
	
	public UserRepositoryImpl() {
		super(UserEntity.class);
	}

	@Override
	public UserEntity findByName(String name) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QUserEntity user = QUserEntity.userEntity;
		return (UserEntity) query.from(user).where(user.name.eq(name)).fetchOne();
	}

}
