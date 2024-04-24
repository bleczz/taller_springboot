package com.pretaller.controllers;

import com.pretaller.domain.dto.UserDto;
import com.pretaller.domain.entities.UserEntity;
import com.pretaller.mappers.Mapper;
import com.pretaller.responses.GeneralResponse;
import com.pretaller.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/api/user/all")
    // implement the method to get all users
    public ResponseEntity<GeneralResponse> getAllUsers(){
        return GeneralResponse.getResponse(HttpStatus.OK, userService.getAllUsers());
    }

    @GetMapping(path = "/api/user/{id}")
    public ResponseEntity<GeneralResponse> getUser(@PathVariable("id") Long id){
        Optional<UserEntity> foundUser = Optional.ofNullable(userService.findOneById(id));
        return foundUser.map(userEntity -> GeneralResponse.getResponse(HttpStatus.OK, userMapper.mapTo(userEntity)))
                .orElse(GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PatchMapping(path = "/api/user/update-password")
    public ResponseEntity<GeneralResponse> updatePassword(@Valid @RequestBody UserDto user){
        UserEntity updatedUserEntity = userService.updatePassword(user.getEmail(), user.getPassword(), user.getNewPassword());
        return GeneralResponse.getResponse(HttpStatus.OK, "User password updated successfully", userMapper.mapTo(updatedUserEntity));
    }
}