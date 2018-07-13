package com.leaf.server.auth.credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CredentialAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CredentialUserDetailsService userDetailsService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		log.debug("~~ additionalAuthenticationChecks()");
		
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("Bad credentials(Credentials is null)");
		}

		String presentedPassword = authentication.getCredentials().toString();

		log.debug("~~ userDetailsPassword = {}", userDetails.getPassword());
		log.debug("~~ presentedPassword   = {}", presentedPassword);
		
		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			throw new BadCredentialsException("Bad credentials(Not matched password)");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		log.debug("~~ retrieveUser()");
		
		UserDetails loadedUser = userDetailsService.loadUserByUsername(username);
		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}
}
