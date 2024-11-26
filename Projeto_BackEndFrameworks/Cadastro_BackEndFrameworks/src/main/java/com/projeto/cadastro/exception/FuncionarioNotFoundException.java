package com.projeto.cadastro.exception;

public class FuncionarioNotFoundException extends RuntimeException{

    public FuncionarioNotFoundException(){
        super("Este funcionario não existe!");
    }
}
