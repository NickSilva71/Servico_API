package com.projeto.cadastro.service;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteFuncionarioTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private DeleteFuncionario deleteFuncionario;

    @Test
    void execute_whenFuncionarioExists() {
        // Dado
        UUID id = UUID.randomUUID();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setName("Funcionario Teste");

        // Quando o funcionário é encontrado pelo ID
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        // Quando
        deleteFuncionario.execute(id);

        // Então
        // Verifica que o método delete do repositório foi chamado
        verify(funcionarioRepository).delete(funcionario);
    }

    @Test
    void execute_whenFuncionarioNotFound() {
        // Dado
        UUID id = UUID.randomUUID();

        // Quando o funcionário não é encontrado pelo ID
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        // Então
        // Verifica que a exceção FuncionarioNotFoundException é lançada
        assertThrows(FuncionarioNotFoundException.class, () -> deleteFuncionario.execute(id));

        // Verifica que o método delete não foi chamado
        verify(funcionarioRepository, never()).delete(any(Funcionario.class));
    }
}
