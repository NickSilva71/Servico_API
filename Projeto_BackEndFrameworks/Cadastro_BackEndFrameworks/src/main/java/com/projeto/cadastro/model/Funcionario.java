package com.projeto.cadastro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity

@Table(name = "funcionarios_cadastrados")
@Data
public class Funcionario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String cpf;

    private String cargo;

    private int age;
}
