package com.leaf.server.auth.credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.leaf.server.auth.UserDetailsImpl;
import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CredentialUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.debug("~~ loadUserByUsername() [username = {}]", username);
		Member member = repository.findByName(username);
		if (member == null) {
			log.debug("~~ member is null");
			throw new UsernameNotFoundException("Not found member, " + username);
		}
		log.debug("~~ member = {}", member.toString());
		return new UserDetailsImpl(member, AuthorityUtils.createAuthorityList(member.getRole()));
	}
}
