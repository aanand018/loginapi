package com.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

//UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByPhoneNumber(String phoneNumber);
}

