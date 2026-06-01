package com.example.mathservice.model;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String erro;
    private String mensagem;
    private String caminhoRequisicao;
    private LocalDateTime timestamp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminhoRequisicao() {
        return caminhoRequisicao;
    }

    public void setCaminhoRequisicao(String caminhoRequisicao) {
        this.caminhoRequisicao = caminhoRequisicao;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
