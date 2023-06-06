package com.project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postBody;
    private String postingTime;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
