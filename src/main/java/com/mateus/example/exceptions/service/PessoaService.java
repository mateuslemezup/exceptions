package com.mateus.example.exceptions.service;


import com.mateus.example.exceptions.exception.PessoaNotFoundException;
import com.mateus.example.exceptions.entity.Pessoa;
import com.mateus.example.exceptions.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll(new Sort(Sort.Direction.ASC, "nome"));
    }

    public Pessoa findOne(Long id){
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("id-" + id));

        return pessoa;
    }

    public Pessoa insert(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
