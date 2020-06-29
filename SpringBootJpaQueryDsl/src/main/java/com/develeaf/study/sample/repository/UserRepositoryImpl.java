package com.develeaf.study.sample.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.develeaf.study.sample.entity.QRoleEntity;
import com.develeaf.study.sample.entity.QTeamEntity;
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
		QTeamEntity team = QTeamEntity.teamEntity;
		QRoleEntity role = QRoleEntity.roleEntity;
		return (UserEntity) query.from(user)
								.join(user.roleList, role)
								.join(user.team, team)
								.where(user.name.eq(name))
								.fetchOne();
	}

}
