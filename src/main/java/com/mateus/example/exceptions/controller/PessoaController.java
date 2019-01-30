package com.mateus.example.exceptions.controller;


import com.mateus.example.exceptions.model.Pessoa;
import com.mateus.example.exceptions.service.PessoaService;
import com.mateus.example.exceptions.config.togglz.MyFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.togglz.core.manager.FeatureManager;
import org.togglz.spring.boot.autoconfigure.TogglzAutoConfiguration;

import java.util.List;

@RestController
public class PessoaController {

    @Autowired
    @Qualifier("featureManagerBuilder")
    private FeatureManager manager;

    @Autowired
    private PessoaService pessoaService;


    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }


    @GetMapping("/pessoas/{id}")
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findOne(id);

        return new ResponseEntity(pessoa, HttpStatus.OK);
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Object> insert(@RequestBody Pessoa pessoa, @RequestHeader HttpHeaders headers) throws NoHandlerFoundException {
        if (!manager.isActive(MyFeatures.INSERT_PERSON)) {
            throw new NoHandlerFoundException("POST", "/pessoas", headers);
        }

        Pessoa savedPessoa = pessoaService.insert(pessoa);

        return new ResponseEntity(savedPessoa, HttpStatus.CREATED);
    }
}
