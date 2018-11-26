package com.thtf.deconfliction.service.util;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thtf.app.domain.User;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.web.rest.errors.InternalServerErrorException;

@Service
public class CommonServiceUtil {
	
	public static User getCurrentLoginUser(UserRepository userRepository) {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		return  userRepository.findOneByLogin(userLogin).orElse(null);
		
	}
	
	
	
	
}
