package com.fvss.vendas.rest.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;


import com.fvss.vendas.exception.RegraNegocioException;
import com.fvss.vendas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicattionControllerAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
}
