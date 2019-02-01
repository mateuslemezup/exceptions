package com.mateus.example.exceptions.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated person ID.", example = "1")
    private Long id;

    @NotNull(message = "nome cannot be empty")
    @Column(nullable = false)
    @ApiModelProperty(notes = "Nome da pessoa", example = "Mateus Spada Leme")
    private String nome;

    @ApiModelProperty(notes = "Idade da pessoa", example = "21")
    private Integer idade;

    public Pessoa(){}

    public Pessoa(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
