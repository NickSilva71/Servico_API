package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFuncionarioByNameUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private GetFuncionarioByNameUseCase getFuncionarioByNameUseCase;

    @Test
    void execute_ReturnFuncionarioList_whenFuncionarioExists() {
        String name = "Funcionario Teste";
        Funcionario funcionario = new Funcionario();
        funcionario.setName(name);
        List<Funcionario> funcionarioList = Arrays.asList(funcionario);


        when(funcionarioRepository.findByName(name)).thenReturn(funcionarioList);


        List<Funcionario> result = getFuncionarioByNameUseCase.execute(name);


        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo(name);
    }

    @Test
    void execute_ThrowFuncionarioNotFoundException_whenNoFuncionarioMatches() {
        String name = "Funcionario Inexistente";


        when(funcionarioRepository.findByName(name)).thenReturn(Arrays.asList());


        assertThrows(FuncionarioNotFoundException.class, () -> getFuncionarioByNameUseCase.execute(name));
    }
}
