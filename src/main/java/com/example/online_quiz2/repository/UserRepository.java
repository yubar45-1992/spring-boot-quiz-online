package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository<User, Long> {
    Optional<User> findById(Long id);

    @Query("select u from User u where u.role = :role")
    List<User> findByProfessor(@Param("role") String professor);


    User findByUsername(String username);

    Page<User> findUsersByRoleIsNotAndStatus(Role role, Status status, Pageable pageable);
}
