package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateFuncionarioTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private UpdateFuncionario updateFuncionario;

    @Test
    void execute_UpdateFuncionario_whenFound() {

        UUID funcionarioId = UUID.randomUUID();
        FuncionarioDto funcionarioDto = new FuncionarioDto("Funcionario Atualizado", "123.456.789-01", "Gerente", 35);
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setName("Funcionario Teste");
        funcionario.setCpf("123.456.789-00");
        funcionario.setCargo("Analista");
        funcionario.setAge(30);

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario updatedFuncionario = updateFuncionario.execute(funcionarioId, funcionarioDto);

        assertNotNull(updatedFuncionario);
        assertEquals("Funcionario Atualizado", updatedFuncionario.getName());
        assertEquals("123.456.789-01", updatedFuncionario.getCpf());
        assertEquals("Gerente", updatedFuncionario.getCargo());
        assertEquals(35, updatedFuncionario.getAge());

        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void execute_ThrowFuncionarioNotFoundException_whenNotFound() {

        UUID funcionarioId = UUID.randomUUID();
        FuncionarioDto funcionarioDto = new FuncionarioDto("Funcionario Atualizado", "12345678901", "Gerente", 35);

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());


        assertThrows(FuncionarioNotFoundException.class, () -> updateFuncionario.execute(funcionarioId, funcionarioDto));

        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(funcionarioRepository, times(0)).save(any(Funcionario.class));
    }
}
