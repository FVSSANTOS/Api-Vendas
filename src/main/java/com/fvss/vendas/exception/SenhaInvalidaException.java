package com.fvss.vendas.exception;

public class SenhaInvalidaException  extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha inválida");
    }
}
