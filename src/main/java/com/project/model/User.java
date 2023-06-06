package com.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "login_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

}
