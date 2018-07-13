package com.leaf.server.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.leaf.server.sample.dto.Member;

public class UserDetailsImpl extends User {

	private static final long serialVersionUID = 1L;

	public UserDetailsImpl(String id, List<GrantedAuthority> authorities) {
		super(id, "", authorities);
	}

	public UserDetailsImpl(Member member, List<GrantedAuthority> authorities) {
		super(member.getUsername(), member.getRealPassword(), authorities);
	}
}
