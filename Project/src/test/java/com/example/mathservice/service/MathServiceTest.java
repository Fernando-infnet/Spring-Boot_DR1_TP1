package com.example.mathservice.service;

import com.example.mathservice.exception.MathException;
import com.example.mathservice.model.MathResponse;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MathServiceTest {

    private final MathService mathService = new MathService();

    @Test
    void testAdicionar() {
        MathResponse response = mathService.adicionar(new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("15"), response.getResultado());
        assertEquals("Adição", response.getOperacao());
    }

    @Test
    void testSubtrair() {
        MathResponse response = mathService.subtrair(new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("5"), response.getResultado());
        assertEquals("Subtração", response.getOperacao());
    }

    @Test
    void testMultiplicar() {
        MathResponse response = mathService.multiplicar(new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("50"), response.getResultado());
        assertEquals("Multiplicação", response.getOperacao());
    }

    @Test
    void testDividir() {
        MathResponse response = mathService.dividir(new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("2.0000000000"), response.getResultado());
        assertEquals("Divisão", response.getOperacao());
    }

    @Test
    void testDividirPorZero() {
        assertThrows(MathException.class, () -> mathService.dividir(new BigDecimal("10"), BigDecimal.ZERO));
    }

    @Test
    void testPotencia() {
        MathResponse response = mathService.potencia(new BigDecimal("2"), new BigDecimal("10"));
        assertEquals(new BigDecimal("1024"), response.getResultado());
        assertEquals("Potência", response.getOperacao());
    }
}
