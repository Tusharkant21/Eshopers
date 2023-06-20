package com.example.springjpa.service;

import com.example.springjpa.exceptions.AuthenticationFailedException;
import com.example.springjpa.model.AuthenticationToken;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepo tokenRepo;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public User getUser(String token){
        final AuthenticationToken authenticationToken  = tokenRepo.findByToken(token);
        if(Objects.isNull(token))
            return null;
        return authenticationToken.getUser();
    }

    public void authenticate(String token){
        //check token is null
        if(Objects.isNull(token)){
            throw  new AuthenticationFailedException("token not present");

        }
        if(Objects.isNull(getUser(token)))
            throw new AuthenticationFailedException("token not valid");



    }
}
