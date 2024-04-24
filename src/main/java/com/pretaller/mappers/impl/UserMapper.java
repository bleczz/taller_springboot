package com.pretaller.mappers.impl;

import com.pretaller.domain.dto.UserDto;
import com.pretaller.domain.entities.UserEntity;
import com.pretaller.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class UserMapper implements Mapper<UserEntity, UserDto> {
    private ModelMapper modelMapper;

    // constructor
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
