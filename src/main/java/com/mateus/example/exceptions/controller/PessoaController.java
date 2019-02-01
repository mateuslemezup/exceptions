package com.mateus.example.exceptions.controller;


import com.mateus.example.exceptions.config.togglz.MyFeatures;
import com.mateus.example.exceptions.entity.Pessoa;
import com.mateus.example.exceptions.service.PessoaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.togglz.core.manager.FeatureManager;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Api(value="Controle de Pessoa", description="Operações para o controle de pessoas")
public class PessoaController {

    @Autowired
    @Qualifier("featureManagerBuilder")
    private FeatureManager manager;

    @Autowired
    private PessoaService pessoaService;

    @ApiOperation(value = "Get a list of person", response = Pessoa[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a person", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findOne(id);

        return new ResponseEntity(pessoa, HttpStatus.OK);
    }

    @ApiOperation(value = "Insert a new person", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person created successfully"),
            @ApiResponse(code = 400, message = "Bad request")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> insert(@ApiParam(value = "Insert a person", required = true) @Valid @RequestBody Pessoa pessoa,
                                         @RequestHeader HttpHeaders headers) throws NoHandlerFoundException {
        if (!manager.isActive(MyFeatures.INSERT_PERSON)) {
            throw new NoHandlerFoundException("POST", "/pessoas", headers);
        }

        Pessoa savedPessoa = pessoaService.insert(pessoa);

        return new ResponseEntity(savedPessoa, HttpStatus.CREATED);
    }
}
