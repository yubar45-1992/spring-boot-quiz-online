package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<UserResponseDto, UserDto> {
}
