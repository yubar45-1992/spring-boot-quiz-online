package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.user.SignUpUserDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import com.example.online_quiz2.dto.user.UserSearchDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


public interface UserService extends BaseDTOService<UserDto, Long> {
    //User save(User user);
    Boolean signUpUserDto(SignUpUserDto signUpUserDto) throws SQLIntegrityConstraintViolationException;

    List<UserResponseDto> findAllUserByAdmin(Integer pageNumber, Integer pageSize, String sortBy);

    Boolean changeUserStatus(Long id);

    UserDto findByUsername(String username);

    List<UserResponseDto> AllByAdvanceSearch(UserSearchDto userSearchDTO);

    UserResponseDto customFindById(Long aLong);

    Optional<User> entityFindById(Long aLong);


/*//    User updateUser(Optional<User> user);
//
//    Optional<User> findCustom(Long id);
//

//
//    List<User> findAllProfessor();
//
//    String splitId(String id);
//
//    List<User> findAllByStudent();
//
//    User checkUserLogin(String username, String password);

 */
}
