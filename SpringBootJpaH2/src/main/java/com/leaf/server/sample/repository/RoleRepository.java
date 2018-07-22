package com.leaf.server.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leaf.server.sample.dto.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
