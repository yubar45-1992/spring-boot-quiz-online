package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.DTONotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.course.CourseResponseDto;
import com.example.online_quiz2.dto.course.CourseStudentResponseDto;
import com.example.online_quiz2.dto.course.SaveCourseDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import com.example.online_quiz2.mapper.CourseMapperEntityWithDto;
import com.example.online_quiz2.mapper.CourseMapperResponseDtoWithDto;
import com.example.online_quiz2.mapper.CourseStudentMapperResponseDtoWithDto;
import com.example.online_quiz2.mapper.UserMapperEntityWithDto;
import com.example.online_quiz2.repository.CourseRepository;
import com.example.online_quiz2.service.CourseService;
import com.example.online_quiz2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

//        BaseDTOServiceImpl<SaveCourseDto,
//                Course,
//                Long,
//                CourseMapper,
@Service
@Transactional(readOnly = true)
public class CourseServiceImpl extends BaseDTOServiceImpl<
        CourseDto, Course, Long, CourseMapperEntityWithDto, CourseRepository>
        implements CourseService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapperEntityWithDto userMapper;

    @Autowired
    private CourseMapperResponseDtoWithDto courseMapperResponseDtoWithDto;

    @Autowired
    private CourseStudentMapperResponseDtoWithDto courseStudentMapperResponseDtoWithDto;

    @Autowired
    private UserDetailService userDetailService;

    public CourseServiceImpl(CourseRepository repository, CourseMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    private static void validationCheck(boolean find, String notFoundMessage, boolean active, String activeMessage) {
        if (!find) {
            throw new BadRequestRuntimeException(notFoundMessage);
        }
        if (active) {
            throw new BadRequestRuntimeException(activeMessage);
        }
    }

    @Override
    @Transactional
    public Boolean addStudentToCourse(Long userId, Long courseId) throws SQLIntegrityConstraintViolationException {
        if (addStudentToCourseValidation(userId, courseId)) {
            UserResponseDto findUser = userService.customFindById(userId);
            Optional<Course> course = repository.findById(courseId);
            User student = new User();
            BeanUtils.copyProperties(findUser, student);

            course.get().getStudents().add(student);
            CourseDto courseDto = mapper.convertEntityToDTO(course.get());
            CourseDto updatedCourse = save(courseDto);
            return true;

        }
        return null;
    }

    @Override
    @Transactional
    public Boolean addTeacherToCourse(Long userId, Long courseId) {
        if (addTeacherToCourseValidation(userId, courseId)) {
            UserResponseDto findTeacher = userService.customFindById(userId);
            User teacher = new User();
            BeanUtils.copyProperties(findTeacher, teacher);
            Course course = repository.findById(courseId).get();
            course.setProfessor(teacher);
            repository.save(course);
            return true;
        }
        throw new BadRequestRuntimeException("validation your data entry failed");
    }

    private boolean addStudentToCourseValidation(Long userId, Long courseId) {
        UserResponseDto findStudent = userService.customFindById(userId);
        CourseResponseDto course = responseFindById(courseId);
        validationCheck(Role.STUDENT.equals(findStudent.getRole()), "only users with student role can add in this position", !Status.ACTIVE.equals(findStudent.getStatus()), "student is not activated");
        if (!CollectionUtils.isEmpty(course.getStudents()) && course.getStudents().contains(findStudent)) {
            throw new BadRequestRuntimeException("this student is already in the course");
        } else return true;
    }

    private boolean addTeacherToCourseValidation(Long userId, Long courseId) {
        UserResponseDto findTeacher = userService.customFindById(userId);
        CourseResponseDto course = responseFindById(courseId);
        validationCheck(findTeacher.getRole().equals(Role.TEACHER), "only users with teachers role can select in this position", !Status.ACTIVE.equals(findTeacher.getStatus()), "teacher is not activated");
        if (course.getProfessor() != null) {
            throw new BadRequestRuntimeException("this course already have a teacher");
        } else return true;
    }

    @Override
    public List<CourseResponseDto> getTeacherCourses() {

        Optional<UserDto> optionalUserDto = userService.findById(getUserId());
        if (optionalUserDto.isEmpty()) {
            throw new BadRequestRuntimeException("teacher not found");
        }
        validationCheck(optionalUserDto.get().getRole().equals(Role.TEACHER),
                "only users with teachers role can select in this position",
                !Status.ACTIVE.equals(optionalUserDto.get().getStatus()), "teacher is not activated");
        List<Course> listCourse = repository.findByProfessorId(getUserId());
        if (listCourse.isEmpty()) {
            throw new BadRequestRuntimeException(" there is no course with this teacher ID");
        }
        List<CourseDto> courseDtos = mapper.convertListEntityToDTOList(listCourse);
        return courseMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(courseDtos);
    }

    @Override
    public CourseResponseDto responseFindById(Long courseId) {
        Optional<CourseDto> optionalCourse = findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new BadRequestRuntimeException("course not found");
        }
        return courseMapperResponseDtoWithDto.convertFirstDtoToSecondDto(optionalCourse.get());
    }


    @Override
    public List<CourseResponseDto> responseFindAll() {
        List<CourseDto> all = findAll();
        if (CollectionUtils.isEmpty(all)) {
            throw new DTONotFoundRuntimeException("course not found");
        }
        return courseMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(all);
    }

    @Override
    public List<CourseStudentResponseDto> responseFindAllWithStudentId(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        List<Course> courseList = repository.findAllByStudentId(getUserId(), paging);
        List<CourseDto> courseDtos = mapper.convertListEntityToDTOList(courseList);
        return courseStudentMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(courseDtos);
    }

    @Transactional
    @Override
    public Boolean responseSave(@Valid SaveCourseDto saveCourseDto)
            throws SQLIntegrityConstraintViolationException {
        if (saveCourseDto.getEndDate().isBefore(saveCourseDto.getStartDate())) {
            throw new BadRequestRuntimeException("end date should be bigger than start date");
        }
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(saveCourseDto, courseDto);
        save(courseDto);
        return true;
    }

    private Long getUserId() {
        return userDetailService.getUserRequestDTO().getId();
    }
}
