package com.leaf.server.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leaf.server.sample.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
