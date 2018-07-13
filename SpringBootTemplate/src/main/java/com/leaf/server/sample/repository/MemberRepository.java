package com.leaf.server.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leaf.server.sample.dto.Member;

/**
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
@Repository
public class MemberRepository {
	
	private List<Member> memberList;
	
	public MemberRepository() {
		memberList = new ArrayList<>();
		memberList.add(new Member("iskwon", 30, "010-1111-1111", "$2a$10$xErm8DtGFti1OcQ3ssfOWuMvqUErs5ZN0p4LLUJB77WiR4hjQaoyK", "ROLE_ADMIN"));
		memberList.add(new Member("hong", 35, "010-1111-2222", "$2a$10$xErm8DtGFti1OcQ3ssfOWuMvqUErs5ZN0p4LLUJB77WiR4hjQaoyK", "ROLE_USER"));
		memberList.add(new Member("lee", 21, "010-1111-3333", "$2a$10$xErm8DtGFti1OcQ3ssfOWuMvqUErs5ZN0p4LLUJB77WiR4hjQaoyK", "ROLE_USER"));
		memberList.add(new Member("kim", 26, "010-1111-4444", "$2a$10$xErm8DtGFti1OcQ3ssfOWuMvqUErs5ZN0p4LLUJB77WiR4hjQaoyK", "ROLE_USER"));
		memberList.add(new Member("son", 41, "010-1111-5555", "$2a$10$xErm8DtGFti1OcQ3ssfOWuMvqUErs5ZN0p4LLUJB77WiR4hjQaoyK", "ROLE_USER"));
	}
	
	public List<Member> findAll() {
		return memberList;
	}
	
	public Member findByName(String username) {
		return memberList.stream()
					.filter(member -> member.getUsername().equals(username))
					.findFirst()
					.orElse(null);
	}
	
	public void add(Member member) {
		synchronized (this) {
			memberList.add(member);
		}
	}
	
	public void modify(Member member) {
		synchronized (this) {
			for (Member oldMember : memberList) {
				if (oldMember.getUsername().equals(member.getUsername())) {
					oldMember = member;
					break;
				}
			}
		}
	}
	
	public void remove(Member member) {
		synchronized (this) {
			Member remove = null;
			for (Member oldMember : memberList) {
				if (oldMember.getUsername().equals(member.getUsername())) {
					remove = oldMember;
					break;
				}
			}
			memberList.remove(remove);			
		}
	}
	
}
