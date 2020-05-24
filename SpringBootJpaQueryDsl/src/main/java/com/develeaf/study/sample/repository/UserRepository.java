package com.develeaf.study.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develeaf.study.sample.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
}
