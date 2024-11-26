package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.repository.FuncionarioRepository;
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
class CreateFuncionarioTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private CreateFuncionario createFuncionario;

    @Test
    void execute() {
        // Dado um FuncionarioDto válido
        FuncionarioDto funcionarioDto = new FuncionarioDto("Carlos Silva", "123.456.789-00", "Desenvolvedor", 28);

        // Criando o objeto Funcionario com os valores fornecidos
        Funcionario savedFuncionario = new Funcionario();
        savedFuncionario.setName("Carlos Silva");
        savedFuncionario.setCpf("123.456.789-00");
        savedFuncionario.setCargo("Desenvolvedor");
        savedFuncionario.setAge(28);

        // Configura o mock para retornar o Funcionario quando o repositório salvar
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(savedFuncionario);

        // Quando
        Funcionario result = createFuncionario.execute(funcionarioDto);

        // Então
        assertThat(result.getName()).isEqualTo("Carlos Silva");
        assertThat(result.getCpf()).isEqualTo("123.456.789-00");
        assertThat(result.getCargo()).isEqualTo("Desenvolvedor");
        assertThat(result.getAge()).isEqualTo(28);

        // Verifica que o repositório save foi chamado exatamente uma vez
        verify(funcionarioRepository).save(any(Funcionario.class));
    }

    @Test
    void executeWithVerification() {
        // Dado um FuncionarioDto válido
        FuncionarioDto funcionarioDto = new FuncionarioDto("Carlos Silva", "123.456.789-00", "Desenvolvedor", 28);

        Funcionario savedFuncionario = new Funcionario();
        savedFuncionario.setName("Carlos Silva");
        savedFuncionario.setCpf("123.456.789-00");
        savedFuncionario.setCargo("Desenvolvedor");
        savedFuncionario.setAge(28);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(savedFuncionario);

        // Quando
        createFuncionario.execute(funcionarioDto);

        // Então
        verify(funcionarioRepository).save(any(Funcionario.class));
    }

    @Test
    void executeThrowsExceptionOnSave() {
        // Dado um FuncionarioDto válido
        FuncionarioDto funcionarioDto = new FuncionarioDto("Carlos Silva", "123.456.789-00", "Desenvolvedor", 28);

        // Configura o mock para lançar uma exceção no save
        when(funcionarioRepository.save(any(Funcionario.class))).thenThrow(RuntimeException.class);


        assertThrows(RuntimeException.class, () -> createFuncionario.execute(funcionarioDto));
    }

    @Test
    void executeDoesNotThrow() {
        // Dado um FuncionarioDto válido
        FuncionarioDto funcionarioDto = new FuncionarioDto("Carlos Silva", "123.456.789-00", "Desenvolvedor", 28);

        Funcionario savedFuncionario = new Funcionario();
        savedFuncionario.setName("Carlos Silva");
        savedFuncionario.setCpf("123.456.789-00");
        savedFuncionario.setCargo("Desenvolvedor");
        savedFuncionario.setAge(28);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(savedFuncionario);


        assertDoesNotThrow(() -> createFuncionario.execute(funcionarioDto));
    }
}
