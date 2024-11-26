package com.projeto.cadastro.service;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllDepartamentosTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private GetAllDepartamentos getAllDepartamentos;

    @Test
    void execute_whenDepartmentsExist() {
        // Dado
        Departamento departamento1 = new Departamento();
        departamento1.setName("Departamento 1");

        Departamento departamento2 = new Departamento();
        departamento2.setName("Departamento 2");

        List<Departamento> departamentos = Arrays.asList(departamento1, departamento2);

        // Quando
        when(departamentoRepository.findAll()).thenReturn(departamentos);

        // Quando
        List<Departamento> result = getAllDepartamentos.execute();

        // Então
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Departamento 1");
        assertThat(result.get(1).getName()).isEqualTo("Departamento 2");
    }

    @Test
    void execute_whenNoDepartmentsExist() {
        // Dado
        List<Departamento> departamentos = Arrays.asList();

        // Quando
        when(departamentoRepository.findAll()).thenReturn(departamentos);

        // Quando
        List<Departamento> result = getAllDepartamentos.execute();

        // Então
        assertThat(result).isEmpty();
    }
}
