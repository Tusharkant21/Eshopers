package com.example.springjpa.service;

import com.example.springjpa.dtos.user.ResponseDto;
import com.example.springjpa.dtos.user.SigninDto;
import com.example.springjpa.dtos.user.SigninResponseDto;
import com.example.springjpa.dtos.user.SignupDto;
import com.example.springjpa.exceptions.AuthenticationFailedException;
import com.example.springjpa.exceptions.CustomException;
import com.example.springjpa.model.AuthenticationToken;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signup(SignupDto signupDto){
        // check if user is already present
       if(Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))){
           throw new CustomException("User already present");
       }
       //encrypting password
       String encryptedpassword = signupDto.getPassword();
       try{
           encryptedpassword = hasPassword(signupDto.getPassword());
       }
       catch (NoSuchAlgorithmException e){
           e.printStackTrace();

       }
          //save the user
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
                encryptedpassword);
        userRepo.save(user);

       final AuthenticationToken authenticationToken= new AuthenticationToken(user);
       authenticationService.saveConfirmationToken(authenticationToken);

       ResponseDto responseDto = new ResponseDto("success","user created succesfully");
       return responseDto;
    }
    private String hasPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDto signIn(SigninDto signinDto) {
        // find user by email;
        User user = userRepo.findByEmail(signinDto.getEmail());
        if(Objects.isNull(user)){
            throw new AuthenticationFailedException("user not found");
        }
        //hash the password;
        try {
           if(!user.getPassword().equals(hasPassword(signinDto.getPassword()))){
               throw new AuthenticationFailedException("wrong password");

            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //if password match;

        AuthenticationToken token = authenticationService.getToken(user);


        //retrive the token;
        if(Objects.isNull(token)){
            throw  new CustomException("token is not present");

        }
        return new SigninResponseDto("succes",token.getToken());

        //return token;

    }
}
