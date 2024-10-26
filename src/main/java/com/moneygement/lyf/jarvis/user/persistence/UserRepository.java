package com.moneygement.lyf.jarvis.user.persistence;

import com.moneygement.lyf.jarvis.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUserId(String userId);
}
