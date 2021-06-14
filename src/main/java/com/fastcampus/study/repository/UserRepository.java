package com.fastcampus.study.repository;

import com.fastcampus.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT FROM User WHERE account = ?;
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    // SELECT FROM User WHERE account = ? AND email = ?;
    Optional<User> findByAccountAndEmail(String account, String email);
}
