package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario execute(FuncionarioDto funcionarioDto) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return funcionarioRepository.save(funcionario);
    }
}
