package com.example.mathservice.model;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MathRequest {

    @NotNull(message = "O primeiro operando não pode ser nulo")
    private BigDecimal operando1;

    @NotNull(message = "O segundo operando não pode ser nulo")
    private BigDecimal operando2;

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
}
