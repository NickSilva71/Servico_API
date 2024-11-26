package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.DepartamentoDto;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDepartamentoTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @Mock
    private Departamento departamento;

    @InjectMocks
    private CreateDepartamento createDepartamento;

    @Test
    void execute() {

        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Teste", 10);

        // Cria o objeto `Departamento` manualmente e configura os valores usando setters
        Departamento savedDepartamento = new Departamento();
        savedDepartamento.setName("Departamento Teste");
        savedDepartamento.setQuantidadeFuncionarios(10);

        // Configura o mock para retornar o objeto criado acima
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(savedDepartamento);


        Departamento result = createDepartamento.execute(departamentoDto);


        assertThat(result.getName()).isEqualTo("Departamento Teste");
        assertThat(result.getQuantidadeFuncionarios()).isEqualTo(10);


        verify(departamentoRepository).save(any(Departamento.class));
    }


    @Test
    void executeWithVerification() {
        // Given
        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Teste", 10);
        Departamento savedDepartamento = new Departamento();

        when(departamentoRepository.save(any(Departamento.class))).thenReturn(savedDepartamento);

        // When
        createDepartamento.execute(departamentoDto);

        // Then
        verify(departamentoRepository).save(any(Departamento.class));
    }

    @Test
    void executeThrowsExceptionOnSave() {
        // Given
        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Teste", 10);
        when(departamentoRepository.save(any(Departamento.class))).thenThrow(RuntimeException.class);

        // When & Then
        assertThrows(RuntimeException.class, () -> createDepartamento.execute(departamentoDto));
    }

    @Test
    void executeDoesNotThrow() {
        // Given
        DepartamentoDto departamentoDto = new DepartamentoDto("Departamento Teste", 10);
        Departamento savedDepartamento = new Departamento();

        when(departamentoRepository.save(any(Departamento.class))).thenReturn(savedDepartamento);

        // When & Then
        assertDoesNotThrow(() -> createDepartamento.execute(departamentoDto));
    }
}

