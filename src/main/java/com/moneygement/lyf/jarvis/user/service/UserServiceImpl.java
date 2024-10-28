package com.moneygement.lyf.jarvis.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.user.domain.User;
import com.moneygement.lyf.jarvis.user.persistence.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public Optional<User> findById(String userId) {
		return userRepository.findUserByUserId(userId);
	}
}

