package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperEntityWithDto extends BaseMapperEntityWithDto<User, UserDto, Long> {
}
