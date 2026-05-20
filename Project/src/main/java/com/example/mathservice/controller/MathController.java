package com.example.mathservice.controller;

import com.example.mathservice.model.MathRequest;
import com.example.mathservice.model.MathResponse;
import com.example.mathservice.service.MathService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/adicionar")
    public ResponseEntity<MathResponse> adicionar(@Valid @RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.adicionar(request.getOperando1(), request.getOperando2()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subtrair")
    public ResponseEntity<MathResponse> subtrair(@Valid @RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.subtrair(request.getOperando1(), request.getOperando2()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiplicar")
    public ResponseEntity<MathResponse> multiplicar(@Valid @RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.multiplicar(request.getOperando1(), request.getOperando2()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dividir")
    public ResponseEntity<MathResponse> dividir(@Valid @RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.dividir(request.getOperando1(), request.getOperando2()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/potencia")
    public ResponseEntity<MathResponse> potencia(@Valid @RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.potencia(request.getOperando1(), request.getOperando2()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Math Service está funcionando!");
    }
}
