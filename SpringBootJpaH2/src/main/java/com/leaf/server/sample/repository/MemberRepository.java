package com.leaf.server.sample.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leaf.server.sample.dto.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	public Member findByMemberId(String memberId);
	
	@Modifying
	@Query("UPDATE Member SET NAME = :#{#member.name}, AGE = :#{#member.age} WHERE MEMBER_ID = :#{#member.memberId}")
	@Transactional
	public void updateByMemberId(@Param("member") Member member);
	
	@Modifying
	@Query("DELETE FROM Member WHERE MEMBER_ID = :memberId")
	@Transactional
	public void deleteByMemberId(@Param("memberId") String memberId);
}
