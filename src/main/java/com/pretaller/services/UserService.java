package com.pretaller.services;

import com.pretaller.domain.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity signUp(UserEntity userEntity);

    UserEntity signIn(UserEntity userEntity);

    UserEntity findByEmail(UserEntity userEntity);

    UserEntity findOneById(Long id);

    List<UserEntity> getAllUsers();

    UserEntity updatePassword(String email, String currentPassword, String newPassword);


    // utils
    boolean existsByEmail(String email);
}
