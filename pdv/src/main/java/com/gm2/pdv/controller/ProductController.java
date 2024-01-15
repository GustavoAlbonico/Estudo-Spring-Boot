package com.gm2.pdv.controller;

import com.gm2.pdv.dto.ResponseDTO;
import com.gm2.pdv.entity.Product;
import com.gm2.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(@Autowired ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Product product){
        try {
            productRepository.save(product);
            return new ResponseEntity<>(new ResponseDTO("Produto cadastrado com sucesso!"), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody Product product){
        try{
            productRepository.save(product);
            return new ResponseEntity<>(new ResponseDTO("Produto editado com sucesso!"), HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Produto removido com sucesso!"),HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
