package com.gm2.pdv.controller;

import com.gm2.pdv.dto.ResponseDTO;
import com.gm2.pdv.entity.User;
import com.gm2.pdv.exceptions.NoItemException;
import com.gm2.pdv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody User user){
        try{
            user.setEnable(true);
            userService.save(user);
            return new ResponseEntity<>(new ResponseDTO("Usuario cadastrado com sucesso!"),HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody User user){
        try{
            userService.update(user);
            return new ResponseEntity<>(new ResponseDTO("Usuario editado com sucesso!"), HttpStatus.OK);
        } catch (NoItemException error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try{
            userService.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Usuário removido com sucesso!"),HttpStatus.OK);
        }catch (EmptyResultDataAccessException error){
            return new ResponseEntity<>(new ResponseDTO("Não foi possível localizar o usuário"),HttpStatus.BAD_REQUEST);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
