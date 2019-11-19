package com.hongda.controller;

import com.hongda.dao.UserDao;
import com.hongda.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long id){
        Optional<User> user = userDao.findById(id);
        user.get().setPassword("");
        return user;
    }
//
    @PostMapping("/user/login")
    public Optional<User> login(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        Optional<User> user = userDao.findUserByEmail(email);
        if(user.isPresent()){
            if (user.get().getPassword().equals(password)){
                user.get().setPassword("");
                return user;
            }else {
                return Optional.empty();
            }
        }else {
            return Optional.empty();
        }
    }

    @PostMapping("/user/register")
    public Optional<User> register(@RequestBody Map<String, String> requestJson){
        String email = requestJson.get("email");
        String password = requestJson.get("password");
        String nickname = requestJson.get("nickname");
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isPresent()){
            return Optional.empty();
        }else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setNickname(nickname);
            userDao.save(newUser);
            Optional<User> newInsertedUser = userDao.findUserByEmail(email);
            newInsertedUser.get().setPassword("");
            return newInsertedUser;
        }
    }
}
