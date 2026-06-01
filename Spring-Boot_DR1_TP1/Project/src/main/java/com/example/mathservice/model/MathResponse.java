package com.example.mathservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MathResponse {

    private BigDecimal resultado;
    private String operacao;
    private BigDecimal operando1;
    private BigDecimal operando2;
    private LocalDateTime timestamp;
    private String mensagem;
    private String statusCode;

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public BigDecimal getOperando1() {
        return operando1;
    }

    public void setOperando1(BigDecimal operando1) {
        this.operando1 = operando1;
    }

    public BigDecimal getOperando2() {
        return operando2;
    }

    public void setOperando2(BigDecimal operando2) {
        this.operando2 = operando2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
