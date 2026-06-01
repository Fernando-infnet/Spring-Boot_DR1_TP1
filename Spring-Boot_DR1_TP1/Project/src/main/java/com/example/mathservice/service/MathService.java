package com.example.mathservice.service;

import com.example.mathservice.exception.MathException;
import com.example.mathservice.model.MathResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public MathResponse adicionar(BigDecimal operando1, BigDecimal operando2) {
        BigDecimal resultado = operando1.add(operando2);
        return buildResponse(resultado, "Adição", operando1, operando2, "Adição realizada com sucesso");
    }

    public MathResponse subtrair(BigDecimal operando1, BigDecimal operando2) {
        BigDecimal resultado = operando1.subtract(operando2);
        return buildResponse(resultado, "Subtração", operando1, operando2, "Subtração realizada com sucesso");
    }

    public MathResponse multiplicar(BigDecimal operando1, BigDecimal operando2) {
        BigDecimal resultado = operando1.multiply(operando2);
        return buildResponse(resultado, "Multiplicação", operando1, operando2, "Multiplicação realizada com sucesso");
    }

    public MathResponse dividir(BigDecimal operando1, BigDecimal operando2) {
        if (operando2.compareTo(BigDecimal.ZERO) == 0) {
            throw new MathException("Divisão por zero não é permitida");
        }
        BigDecimal resultado = operando1.divide(operando2, 10, RoundingMode.HALF_UP);
        return buildResponse(resultado, "Divisão", operando1, operando2, "Divisão realizada com sucesso");
    }

    public MathResponse potencia(BigDecimal operando1, BigDecimal operando2) {
        if (operando2.scale() > 0) {
            throw new MathException("Expoente deve ser inteiro");
        }
        int expoente = operando2.intValueExact();
        BigDecimal resultado = operando1.pow(expoente);
        return buildResponse(resultado, "Potência", operando1, operando2, "Potência realizada com sucesso");
    }

    private MathResponse buildResponse(BigDecimal resultado, String operacao, BigDecimal op1, BigDecimal op2, String mensagem) {
        MathResponse response = new MathResponse();
        response.setResultado(resultado);
        response.setOperacao(operacao);
        response.setOperando1(op1);
        response.setOperando2(op2);
        response.setTimestamp(LocalDateTime.now());
        response.setMensagem(mensagem);
        response.setStatusCode("200");
        return response;
    }
}
