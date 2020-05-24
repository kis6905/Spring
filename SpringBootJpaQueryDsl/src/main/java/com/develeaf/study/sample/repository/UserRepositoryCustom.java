package com.develeaf.study.sample.repository;

import com.develeaf.study.sample.entity.UserEntity;

public interface UserRepositoryCustom {
	UserEntity findByName(String userId);
}
