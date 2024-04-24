package com.pretaller.config;

import com.pretaller.domain.dto.UserDto;
import com.pretaller.domain.entities.UserEntity;
import com.pretaller.mappers.Mapper;
import com.pretaller.mappers.impl.UserMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Bean
    public Mapper<UserEntity, UserDto> userMapper(ModelMapper modelMapper) {
        return new UserMapper(modelMapper);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}