package com.kis.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kis.boot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
