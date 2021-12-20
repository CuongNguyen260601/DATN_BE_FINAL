package com.localbrand.repository;

import com.localbrand.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findFirstByEmailEqualsIgnoreCase(String email);
    Optional<User> findFirstByPhoneNumber(String phoneNumber);
}