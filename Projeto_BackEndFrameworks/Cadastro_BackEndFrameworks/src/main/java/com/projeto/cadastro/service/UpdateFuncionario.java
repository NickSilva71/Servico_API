package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario execute(UUID id, FuncionarioDto funcionarioDto) {
        Optional<Funcionario> foundFuncionario = funcionarioRepository.findById(id);

        if (foundFuncionario.isEmpty()) {
            throw new FuncionarioNotFoundException();
        }

        Funcionario funcionario = foundFuncionario.get();
        BeanUtils.copyProperties(funcionarioDto, funcionario);

        return funcionarioRepository.save(funcionario);
    }
}
