package com.pretaller.repositories;

import com.pretaller.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findOneById(Long id);
    boolean existsByEmail(String email);
}