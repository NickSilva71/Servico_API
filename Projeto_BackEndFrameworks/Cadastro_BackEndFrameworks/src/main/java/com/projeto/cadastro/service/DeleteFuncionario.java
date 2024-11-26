package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void execute(UUID id) {
        Optional<Funcionario> foundFuncionario = funcionarioRepository.findById(id);

        if (foundFuncionario.isEmpty()) {
            throw new FuncionarioNotFoundException();
        }

        funcionarioRepository.delete(foundFuncionario.get());
    }
}
