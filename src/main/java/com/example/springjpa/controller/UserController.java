package com.example.springjpa.controller;

import com.example.springjpa.dtos.user.ResponseDto;
import com.example.springjpa.dtos.user.SigninDto;
import com.example.springjpa.dtos.user.SigninResponseDto;
import com.example.springjpa.dtos.user.SignupDto;
import com.example.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController

public class UserController {
@Autowired
    UserService userService;
    // signup
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return  userService.signup(signupDto);
    }


    //sigin

    @PostMapping("signin")
    public SigninResponseDto signIn(@RequestBody SigninDto signinDto){
        return userService.signIn(signinDto);
    }


}
