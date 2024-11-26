package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
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
class GetDepartamentoByNameUseCaseTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private GetDepartamentoByNameUseCase getDepartamentoByNameUseCase;

    @Test
    void execute_ReturnDepartamento_whenDepartamentoExists() {
        // Given
        String name = "Departamento Teste";
        Departamento departamento1 = new Departamento();
        departamento1.setName("Departamento Teste");

        List<Departamento> departamentos = Arrays.asList(departamento1);

        when(departamentoRepository.findByName(name)).thenReturn(departamentos);

        // When
        List<Departamento> result = getDepartamentoByNameUseCase.execute(name);

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo("Departamento Teste");
    }

    @Test
    void execute_ThrowDepartamentoNotFoundException_whenDepartamentoNotFound() {
        // Given
        String name = "Departamento Inexistente";

        // Quando o repositório não encontrar departamentos
        when(departamentoRepository.findByName(name)).thenReturn(Arrays.asList());

        // Then
        assertThrows(DepartamentoNotFoundException.class, () -> getDepartamentoByNameUseCase.execute(name));
    }

}
