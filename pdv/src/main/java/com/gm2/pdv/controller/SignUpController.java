package com.gm2.pdv.controller;

import com.gm2.pdv.dto.ResponseDTO;
import com.gm2.pdv.dto.UserDTO;
import com.gm2.pdv.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {

    private UserService userService;

    public SignUpController(@Autowired UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody UserDTO user){
        try{
            user.setEnable(true);
            userService.save(user);
            return new ResponseEntity<>(new ResponseDTO("Usuario cadastrado com sucesso!"), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
