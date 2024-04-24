package com.pretaller.services.impl;

import com.pretaller.domain.entities.UserEntity;
import com.pretaller.exception.UserNotFoundException;
import com.pretaller.repositories.UserRepository;
import com.pretaller.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserEntity signUp(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new IllegalArgumentException("Invalid email. Use another one");
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity signIn(UserEntity userEntity) {
        UserEntity foundUser = userRepository.findByEmail(userEntity.getEmail());
        if (foundUser == null) {
            throw new UserNotFoundException("User not found in database");
        }
        if (!bCryptPasswordEncoder.matches(userEntity.getPassword(), foundUser.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return foundUser;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity findByEmail(UserEntity userEntity) {
        return userRepository.findByEmail(userEntity.getEmail());
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findOneById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public UserEntity updatePassword(String email, String currentPassword, String newPassword) {
        UserEntity foundUser = userRepository.findByEmail(email);

        if (foundUser == null) {
            throw new UserNotFoundException("User not found in database");
        }

        if (currentPassword == null || newPassword == null) {
            throw new IllegalArgumentException("Current password or new password cannot be null");
        }

        if (!bCryptPasswordEncoder.matches(currentPassword, foundUser.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        foundUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return userRepository.save(foundUser);
    }
}