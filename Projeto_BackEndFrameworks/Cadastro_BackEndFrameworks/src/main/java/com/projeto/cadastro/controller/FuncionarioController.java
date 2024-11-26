package com.projeto.cadastro.controller;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import com.projeto.cadastro.service.CreateFuncionario;
import com.projeto.cadastro.service.DeleteFuncionario;
import com.projeto.cadastro.service.GetFuncionarioByNameUseCase;
import com.projeto.cadastro.service.UpdateFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("functionary")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CreateFuncionario createFuncionario;

    @Autowired
    private GetFuncionarioByNameUseCase getFuncionarioByNameUseCase;

    @Autowired
    private DeleteFuncionario deleteFuncionario;

    @Autowired
    private UpdateFuncionario updateFuncionario;

    @PostMapping
    public ResponseEntity<Funcionario> createPessoa(@RequestBody FuncionarioDto funcionarioDto) {
        Funcionario createdFuncionario = createFuncionario.execute(funcionarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuncionario);
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<Object> getFuncionarioByName(@PathVariable String name) {
        try {
            List<Funcionario> foundFuncionario = getFuncionarioByNameUseCase.execute(name);
            return ResponseEntity.status(HttpStatus.OK).body(foundFuncionario);
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFuncionarioById(@PathVariable UUID id) {
        try {
            deleteFuncionario.execute(id);
            return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso!");
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFuncionarioById(@RequestBody FuncionarioDto funcionarioDto,
                                                   @PathVariable UUID id) {
        try{
            Funcionario updatedFuncionario = updateFuncionario.execute(id, funcionarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFuncionario);
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}


