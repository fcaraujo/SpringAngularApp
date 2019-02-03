package com.sales.management.data.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sales.management.data.entities.User;

public interface UserRespostory extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
