package com.rick.backend.db.dao;

import com.rick.backend.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserAct(String userAct);

    boolean existsByUserAct(String userAct);

    boolean existsByUserId(String userId);
}
