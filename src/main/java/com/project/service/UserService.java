package com.project.service;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface UserService extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User getById(Long id);
}
