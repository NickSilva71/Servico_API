package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.DepartamentoDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateDepartamentoTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private UpdateDepartamento updateDepartamento;

    @Test
    void execute_UpdateDepartamento_whenFound() {

        UUID departamentoId = UUID.randomUUID();
        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Atualizado", 15);
        Departamento departamento = new Departamento();
        departamento.setId(departamentoId);
        departamento.setName("Departamento Teste");
        departamento.setQuantidadeFuncionarios(10);

        when(departamentoRepository.findById(departamentoId)).thenReturn(Optional.of(departamento));
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(departamento);


        Departamento updatedDepartamento = updateDepartamento.execute(departamentoId, departamentoDto);


        assertNotNull(updatedDepartamento);
        assertEquals("Departamento Atualizado", updatedDepartamento.getName());
        assertEquals(15, updatedDepartamento.getQuantidadeFuncionarios());

        verify(departamentoRepository, times(1)).findById(departamentoId);
        verify(departamentoRepository, times(1)).save(any(Departamento.class));
    }

    @Test
    void execute_ThrowDepartamentoNotFoundException_whenNotFound() {

        UUID departamentoId = UUID.randomUUID();
        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Atualizado", 15);

        when(departamentoRepository.findById(departamentoId)).thenReturn(Optional.empty());

        assertThrows(DepartamentoNotFoundException.class, () -> updateDepartamento.execute(departamentoId, departamentoDto));

        verify(departamentoRepository, times(1)).findById(departamentoId);
        verify(departamentoRepository, times(0)).save(any(Departamento.class));
    }
}
