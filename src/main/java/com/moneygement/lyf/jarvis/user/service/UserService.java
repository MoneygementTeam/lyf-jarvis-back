package com.moneygement.lyf.jarvis.user.service;

import java.util.Optional;

import com.moneygement.lyf.jarvis.user.domain.User;

public interface UserService {

	Optional<User> findById(String userId);
}
