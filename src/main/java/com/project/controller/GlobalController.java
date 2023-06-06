package com.project.controller;

import com.project.model.Post;
import com.project.model.User;
import com.project.service.PostService;
import com.project.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")

public class GlobalController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;


    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody User signUpRequest) {
        try {
            User result = userService.findByUsername(signUpRequest.getUsername());
            if(result!=null){
                return new ResponseEntity<User>(result,HttpStatus.OK);
            }else{
                userService.save(signUpRequest);
                return new ResponseEntity<User>(signUpRequest,HttpStatus.CREATED);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginRequest) {
        try {
            User result = userService.findByUsername(loginRequest.getUsername());
            if(result != null){
                if(result.getPassword().matches(loginRequest.getPassword())){
                    return new ResponseEntity<User>(result,HttpStatus.OK);
                }else{
                    return new ResponseEntity<User>(result,HttpStatus.UNAUTHORIZED);
                }
            }else{
                return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @PostMapping("/submitPost/{userId}/post")
    private ResponseEntity<Post> submitPostByUserId (@PathVariable(value = "userId") Long userId, @RequestBody Post post){
        try{
            User user = userService.getById(userId);
            if(user!=null){
                post.setUser(user);
                post.setPostBody(post.getPostBody());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                post.setPostingTime(date.toString());
                postService.save(post);
                return new ResponseEntity<Post>(post, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<Post>(post, HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/allUserPosts")
    private ResponseEntity<List<User>> getAllUserPosts(){
        try{
            List<User> userAndPostList = userService.findAll();
            return new ResponseEntity<>(userAndPostList,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
