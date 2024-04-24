package com.pretaller.controllers;

import com.pretaller.domain.dto.UserDto;
import com.pretaller.domain.entities.UserEntity;
import com.pretaller.mappers.Mapper;
import com.pretaller.responses.GeneralResponse;
import com.pretaller.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;


    public AuthController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/api/auth/signUp")
    public ResponseEntity<GeneralResponse> signUp(@Valid @RequestBody UserDto user){
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.signUp(userEntity);
        return GeneralResponse.getResponse(HttpStatus.CREATED, "User signed up successfully", userMapper.mapTo(savedUserEntity));
    }

    @PostMapping(path = "/api/auth/signIn")
    public ResponseEntity<GeneralResponse> logIn(@RequestBody UserDto user){
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.signIn(userEntity);
        return GeneralResponse.getResponse(HttpStatus.OK, "User logged in successfully", userMapper.mapTo(savedUserEntity));
    }
}
