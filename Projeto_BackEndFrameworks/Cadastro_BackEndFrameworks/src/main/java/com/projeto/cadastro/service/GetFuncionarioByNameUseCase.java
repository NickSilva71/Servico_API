package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFuncionarioByNameUseCase {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> execute(String name) {
        List<Funcionario> foundFuncionario = funcionarioRepository.findByName(name);

        if (foundFuncionario.isEmpty()) {
            throw new FuncionarioNotFoundException();
        }
        return foundFuncionario;
    }
}

