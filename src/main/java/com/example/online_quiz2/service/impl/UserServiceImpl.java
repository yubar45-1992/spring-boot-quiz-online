package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.DTONotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.user.*;
import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import com.example.online_quiz2.mapper.UserMapperEntityWithDto;
import com.example.online_quiz2.mapper.UserMapperResponseDtoWithDto;
import com.example.online_quiz2.repository.UserRepository;
import com.example.online_quiz2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends
        BaseDTOServiceImpl<UserDto,
                User,
                Long,
                UserMapperEntityWithDto,
                UserRepository>
        implements UserService {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapperResponseDtoWithDto userMapperResponseDtoWithDto;

    public UserServiceImpl(UserRepository repository, UserMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

//    @Transactional
//    public UserRequestDto save(UserRequestDto userRequestDTO) throws SQLIntegrityConstraintViolationException {
//        return super.save(userRequestDTO);
//    }


    @Override
    @Transactional
    public Boolean signUpUserDto(@Valid SignUpUserDto signUpUserDto) throws SQLIntegrityConstraintViolationException {
        if (signUpUserDto.getRole().equals(Role.ADMIN)) {
            throw new BadRequestRuntimeException("you can not sign up with user role admin");
        }
        UserRequestDto userRequestDTO = new UserRequestDto();
        signUpUserDto.setPassword(passwordEncoder.encode(signUpUserDto.getPassword()));
        BeanUtils.copyProperties(signUpUserDto, userRequestDTO);
        userRequestDTO.setStatus(Status.PENDING);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequestDTO, userDto);
        super.save(userDto);
        return true;
    }

    @Override
    public UserDto findByUsername(String username) {
        return mapper.convertEntityToDTO(repository.
                findByUsername(username));
    }

    @Override
    public Optional<User> entityFindById(Long userId) {

        return repository.findById(userId);
    }

    @Override
    public UserResponseDto customFindById(Long aLong) {
        Optional<UserDto> optionalUserDto = super.findById(aLong);
        if (optionalUserDto.isEmpty()) {
            throw new BadRequestRuntimeException("user not found");
        }
        return userMapperResponseDtoWithDto.convertFirstDtoToSecondDto(optionalUserDto.get());
    }

    @Override
    public List<UserResponseDto> findAllUserByAdmin(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<User> result = repository.findUsersByRoleIsNotAndStatus(Role.ADMIN, Status.PENDING, paging);
        if (result.hasContent()) {
            List<UserDto> userDtos = mapper.convertListEntityToDTOList(result.getContent());
            return userMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(userDtos);
        }
        throw new DTONotFoundRuntimeException("not verified users not found");
    }


    @Override
    @Transactional
    public Boolean changeUserStatus(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            if (Status.ACTIVE.equals(user.get().getStatus())) {
                throw new BadRequestRuntimeException("user has already activated");
            }
            user.get().setStatus(Status.ACTIVE);
            repository.save(user.get());
            return true;
        }
        throw new BadRequestRuntimeException("user not found");
    }

    @Override
    public long count() {
        return repository.count();
    }


    @Override
    public List<UserResponseDto> AllByAdvanceSearch(UserSearchDto userSearchDTO) {

        Pageable paging = PageRequest.of(userSearchDTO.getPageNumber(),
                userSearchDTO.getPageSize(), Sort.by(userSearchDTO.getSortBy()));

        Page<User> all = repository.findAll(
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    setNotRolePredicate(predicates, root, criteriaBuilder, Role.ADMIN);
                    setFirstNamePredicate(predicates, root, criteriaBuilder, userSearchDTO.getFirstName());
                    setLastNamePredicate(predicates, root, criteriaBuilder, userSearchDTO.getLastName());
                    setUsernamePredicate(predicates, root, criteriaBuilder, userSearchDTO.getUsername());
                    setGenderPredicate(predicates, root, criteriaBuilder, userSearchDTO.getGender());
                    setRolePredicate(predicates, root, criteriaBuilder, userSearchDTO.getRole());
                    setStatusPredicate(predicates, root, criteriaBuilder, userSearchDTO.getStatus());
                    return criteriaBuilder.and(
                            predicates.toArray(new Predicate[0])
                    );
                }
                , paging);

        if (all.hasContent()) {
            List<User> content = all.getContent();
            List<UserResponseDto> userResponseDtos = new ArrayList<>();

            List<UserDto> userDtos = mapper.convertListEntityToDTOList(content);
            userDtos.forEach(userDto -> {
                UserResponseDto userResponse = new UserResponseDto();
                BeanUtils.copyProperties(userDto, userResponse);
                userResponseDtos.add(userResponse);
            });
            return userResponseDtos;
        }
        throw new DTONotFoundRuntimeException("user not found with your search content");
    }


    private void setFirstNamePredicate(List<Predicate> predicates, Root<User> root,
                                       CriteriaBuilder criteriaBuilder, String name) {
        if (name != null && !name.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("firstName"),
                            "%" + name + "%"
                    )
            );
        }
    }


    private void setNotRolePredicate(List<Predicate> predicates, Root<User> root,
                                     CriteriaBuilder criteriaBuilder, Role role) {
        predicates.add(
                criteriaBuilder.notEqual(
                        root.get("role"),
                        role
                )
        );
    }


    private void setLastNamePredicate(List<Predicate> predicates, Root<User> root,
                                      CriteriaBuilder criteriaBuilder, String name) {
        if (StringUtils.hasText(name)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("lastName"),
                            "%" + name + "%"
                    )
            );
        }
    }

    private void setStatusPredicate(List<Predicate> predicates, Root<User> root
            , CriteriaBuilder criteriaBuilder, Status status) {
        if (status != null && StringUtils.hasText(status.name())) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("status"),
                            status)
            );
        }
    }

    private void setRolePredicate(List<Predicate> predicates, Root<User> root,
                                  CriteriaBuilder criteriaBuilder, Role role) {
        if (role != null && StringUtils.hasText(role.name())) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("role"),
                            role
                    )
            );
        }
    }

    private void setUsernamePredicate(List<Predicate> predicates, Root<User> root,
                                      CriteriaBuilder criteriaBuilder, String name) {
        if (StringUtils.hasText(name)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("username"),
                            "%" + name + "%"
                    )
            );
        }
    }

    private void setGenderPredicate(List<Predicate> predicates, Root<User> root,
                                    CriteriaBuilder criteriaBuilder, Gender gender) {
        if (gender != null && StringUtils.hasText(gender.name())) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("gender"),
                            gender
                    )
            );
        }


    }
}




