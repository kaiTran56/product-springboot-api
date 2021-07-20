package com.tranquyet.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorConfig implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.of(username);
	}

}
