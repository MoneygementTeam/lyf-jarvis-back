package com.moneygement.lyf.jarvis.user.service;

import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.user.persistence.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	private final UserRepository userRepository;
}

