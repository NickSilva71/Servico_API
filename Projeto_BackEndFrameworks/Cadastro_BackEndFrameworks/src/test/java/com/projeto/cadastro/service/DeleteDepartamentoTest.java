package com.projeto.cadastro.service;

import static org.junit.jupiter.api.Assertions.*;

import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteDepartamentoTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DeleteDepartamento deleteDepartamento;

    @Test
    void execute_whenDepartamentoExists() {
        // Dado
        UUID id = UUID.randomUUID();
        Departamento departamento = new Departamento();
        departamento.setId(id);
        departamento.setName("Departamento Teste");

        // Quando o departamento é encontrado pelo ID
        when(departamentoRepository.findById(id)).thenReturn(Optional.of(departamento));

        // Quando
        deleteDepartamento.execute(id);

        // Então
        // Verifica que o método delete do repositório foi chamado
        verify(departamentoRepository).delete(departamento);
    }

    @Test
    void execute_whenDepartamentoNotFound() {
        // Dado
        UUID id = UUID.randomUUID();

        // Quando o departamento não é encontrado pelo ID
        when(departamentoRepository.findById(id)).thenReturn(Optional.empty());

        // Então
        // Verifica que a exceção DepartamentoNotFoundException é lançada
        assertThrows(DepartamentoNotFoundException.class, () -> deleteDepartamento.execute(id));

        // Verifica que o método delete não foi chamado
        verify(departamentoRepository, never()).delete(any(Departamento.class));
    }
}
