package com.kis.boot.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kis.boot.entity.Member;
import com.kis.boot.repositories.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	private static final int ROLE_ADMIN = 1001;
	private static final int ROLE_USER = 1002;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.findOne(username);
		
		logger.info("-> [member = {}]", member.toString());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		String role = "ROLE_USER";
		switch (member.getGradeCode()) {
		case ROLE_ADMIN:
			role = "ROLE_ADMIN";
			break;
		case ROLE_USER:
		default:
			role = "ROLE_USER";
			break;
		}
		
		authorities.add(new SimpleGrantedAuthority(role));
		
		boolean accountNonLocked = member.getPasswordFailCnt() < 5 ? true : false;
		User user = new User(member.getMemberId(), member.getPassword(), true, true, true, accountNonLocked, authorities);
		
		return user;
	}
 
}
