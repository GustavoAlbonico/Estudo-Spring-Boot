package com.gm2.pdv.controller;

import com.gm2.pdv.dto.LoginDTO;
import com.gm2.pdv.dto.ResponseDTO;
import com.gm2.pdv.dto.TokenDTO;
import com.gm2.pdv.repository.UserRepository;
import com.gm2.pdv.security.CustomUserDetailService;
import com.gm2.pdv.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.awt.image.RescaleOp;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private String expiration;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginData){
        try{
            customUserDetailService.verifyUserCredentials(loginData);
            String token = jwtService.generateToken(loginData.getUsername());
            return new ResponseEntity<>(new TokenDTO(token, expiration), HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}


