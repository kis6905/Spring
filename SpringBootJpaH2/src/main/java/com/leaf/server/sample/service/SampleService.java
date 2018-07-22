package com.leaf.server.sample.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.dto.Role;
import com.leaf.server.sample.repository.MemberRepository;
import com.leaf.server.sample.repository.RoleRepository;

@Service
public class SampleService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void postConstruct() {
		roleRepository.save(Role.builder().roleId(1).roleName("ROLE_ADMIN").roleDescription("관리자").build());
		roleRepository.save(Role.builder().roleId(2).roleName("ROLE_USER").roleDescription("사용자").build());
		
		memberRepository.save(Member.builder().memberId("iskwon").name("Kwon il su").age(30).roleId(1).build());
		memberRepository.save(Member.builder().memberId("kdhong").name("홍길동").age(40).roleId(2).build());
		memberRepository.save(Member.builder().memberId("jisu").name("지수").age(24).roleId(2).build());
	}
	
	public List<Member> getMembers() {
		return memberRepository.findAll();
	}
	
	public Member getMember(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
	
	public List<Member> insertMember(Member member) {
		memberRepository.save(member);
		return getMembers();
	}
	
	public List<Member> updateMember(Member member) {
//		Member old = getMember(member.getMemberId());
//		member.setId(old.getId());
//		memberRepository.save(member);
		
		memberRepository.updateByMemberId(member);
		
		return getMembers();
	}
	
	public List<Member> deleteMember(Member member) {
		Member old = getMember(member.getMemberId());
		memberRepository.delete(old);
		return getMembers();
	}
	
}
