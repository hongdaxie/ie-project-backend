package com.hongda.dao;

import com.hongda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

}
