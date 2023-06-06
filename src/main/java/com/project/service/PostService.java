package com.project.service;

import com.project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostService extends JpaRepository<Post, Long> {

}
