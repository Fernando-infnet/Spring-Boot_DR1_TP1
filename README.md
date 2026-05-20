# Relatório: Desenvolvimento de Aplicação Spring Boot com Serviços REST

**Data:** 20 de maio de 2026  
**Status:** Documentação Completa  
**Ferramenta de Construção:** Maven

---

## Índice

1. [Escolha da Ferramenta de Construção](#escolha-da-ferramenta-de-construção)
2. [Iniciação do Projeto](#iniciação-do-projeto)
3. [Gerenciamento de Dependências](#gerenciamento-de-dependências)
4. [Autoconfiguração do Spring Boot](#autoconfiguração-do-spring-boot)
5. [Configuração da IDE](#configuração-da-ide)
6. [Desenvolvimento de Serviços REST](#desenvolvimento-de-serviços-rest)
7. [Testes e Validação](#testes-e-validação)
8. [Repositório Git](#repositório-git)

---

## 1. Escolha da Ferramenta de Construção

### 1.1 Decisão: Maven

**Ferramenta Escolhida:** Apache Maven

### 1.2 Justificativa Detalhada

#### **Por que Maven?**

| Aspecto | Maven | Gradle |
|--------|-------|--------|
| **Curva de Aprendizado** | Mais suave; convenção sobre configuração | Íngreme; mais flexível |
| **Configuração** | XML declarativo (pom.xml) | DSL Groovy/Kotlin |
| **Velocidade** | Mais lenta em builds incrementais | Mais rápida |
| **Comunidade Spring** | Amplamente documentado | Também suportado |
| **Resolução de Erros** | Mais fácil de diagnosticar e corrigir | Requer mais debugging |
| **Dependências** | Gerenciamento robusto e previsível | Dinâmico, às vezes impreciso |

---

## 2. Iniciação do Projeto

### 2.1 Método 1: Spring Initializr (Interface Web)

#### **Passo a Passo:**

1. **Acesso ao Spring Initializr**
   - Navegue até: https://start.spring.io/

2. **Configuração Inicial:**
   ```
   - Project: Maven Project
   - Language: Java
   - Project Metadata:
     * Group: com.example
     * Artifact: math-service
     * Name: Math Service API
     * Description: Serviços REST para operações matemáticas
     * Package name: com.example.mathservice
     * Packaging: Jar
     * Java: 17
   ```

3. **Adição de Dependências:**
   - Spring Web
   - Spring Boot DevTools
   - Lombok
   - Validation

4. **Geração do Projeto:**
   - Clique em "GENERATE"
   - Arquivo `math-service.zip` será baixado
   - Extraia em seu diretório de trabalho

### 2.2 Método 2: Spring Boot CLI

#### **Instalação da Spring Boot CLI:**

```bash
wget https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/3.2.0/spring-boot-cli-3.2.0-bin.tar.gz
tar -xzf spring-boot-cli-3.2.0-bin.tar.gz
export PATH=$PATH:/path/to/spring-3.2.0/bin
```

#### **Criação do Projeto via CLI:**

```bash
spring boot new --from java --name math-service --package-name com.example.mathservice
```

#### **Adição de Dependências via CLI:**

```bash
spring project add \
  web \
  devtools \
  lombok \
  validation
```

### 2.3 Comparação dos Métodos

| Método | Vantagens | Desvantagens | Cenários Ideais |
|--------|-----------|--------------|-----------------|
| **Web Initializr** | Interface visual, preview de dependências, não requer instalação | Requer acesso à internet, menos automatização | Iniciação rápida, projetos únicos, primeiro contato |
| **Spring Boot CLI** | Automatização total, sem internet após instalação, scripting possível | Requer instalação local, curva de aprendizado | Automação, CI/CD, desenvolvimento frequente |

#### **Quando usar cada um:**

- **Spring Initializr Web:**
  - Projetos iniciais isolados
  - Quando você deseja visualizar opções
  - Prototipagem rápida
  - Equipes com diferentes preferências de IDE

- **Spring Boot CLI:**
  - Scripts de automação
  - Pipelines de CI/CD
  - Criação frequente de múltiplos projetos
  - Ambientes sem interface gráfica
  - Integração com ferramentas de desenvolvimento

---

## 3. Gerenciamento de Dependências

### 3.1 Arquivo `pom.xml` Completo

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.example</groupId>
    <artifactId>math-service</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>math-service</name>
    <description>Serviços REST para operações matemáticas</description>

    <properties>
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3.2 Estratégia de Gerenciamento de Dependências

#### **Princípios Aplicados:**

1. **Versionamento Semântico**
   ```
   MAJOR.MINOR.PATCH
   3.2.0
   ├─ MAJOR: Mudanças incompatíveis
   ├─ MINOR: Novas funcionalidades
   └─ PATCH: Correções
   ```

2. **Herança de Parent POM**
   - Spring Boot Starter Parent gerencia versões de dependências
   - Reduz conflitos de versão
   - Aplica configurações padrão automáticamente

3. **Análise e Limpeza**
   ```bash
   # Ver árvore de dependências
   mvn dependency:tree

   # Identificar dependências não utilizadas
   mvn dependency:analyze

   # Atualizar dependências
   mvn versions:display-dependency-updates
   ```

---

## 4. Autoconfiguração do Spring Boot

### 4.1 Entendendo a Autoconfiguração

#### **O que é?**

A autoconfiguração do Spring Boot detecta automaticamente classpath e configura beans sem necessidade de configuração manual.

#### **Como Funciona?**

1. **@SpringBootApplication**
   ```java
   @SpringBootApplication
   public class MathServiceApplication {
       public static void main(String[] args) {
           SpringApplication.run(MathServiceApplication.class, args);
       }
   }
   ```

   Esta anotação combina:
   - `@Configuration`: Marca classe como fonte de configuração
   - `@ComponentScan`: Escaneia componentes no pacote
   - `@EnableAutoConfiguration`: Ativa autoconfiguração

### 4.2 Autoconfiguração na Aplicação

#### **application.properties**

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Application Info
spring.application.name=math-service
app.version=1.0.0
app.description=Serviços REST para operações matemáticas

# Logging Configuration
logging.level.root=INFO
logging.level.com.example.mathservice=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# DevTools Configuration
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true

# Jackson Configuration
spring.jackson.serialization.indent-output=true
spring.jackson.serialization.write-dates-as-timestamps=false
spring.jackson.time-zone=America/Sao_Paulo
```

#### **Configuração Customizada com @Configuration**

```java
package com.example.mathservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ApplicationConfig {

    /**
     * Configuração de CORS para permitir requisições cross-origin
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * Bean customizado para formatação de números
     */
    @Bean
    public DecimalFormat decimalFormat() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df;
    }
}
```

#### **Propriedades Condicionais**

```java
package com.example.mathservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureToggleConfig {

    @Bean
    @ConditionalOnProperty(
        name = "app.features.advanced-math",
        havingValue = "true",
        matchIfMissing = false
    )
    public AdvancedMathService advancedMathService() {
        return new AdvancedMathService();
    }
}
```

### 4.3 Desabilitando Autoconfigurações Específicas

```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class MathServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MathServiceApplication.class, args);
    }
}
```

---

## 5. Configuração da IDE

### 5.1 Configuração do VS Code (Alternativa)

#### **Extensions Necessárias**

1. **Extension Pack for Java** (Microsoft)
   - Language Support for Java
   - Debugger for Java
   - Test Runner for Java
   - Visual Studio IntelliCode

2. **Spring Boot Extension Pack** (Pivotal)
   - Spring Boot Tools
   - Spring Boot Dashboard
   - CloudCode

3. **Adicionais Recomendados**
   - Maven for Java
   - Project Manager for Java
   - REST Client

#### **Configuração launch.json**

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Spring Boot Application",
            "request": "launch",
            "mainClass": "com.example.mathservice.MathServiceApplication",
            "projectName": "math-service",
            "cwd": "${workspaceFolder}",
            "console": "integratedTerminal",
            "preLaunchTask": "maven: clean install"
        }
    ]
}
```

---

## 6. Desenvolvimento de Serviços REST

### 6.1 Estrutura de Diretórios

```
src/main/java/com/example/mathservice/
├── MathServiceApplication.java
├── config/
│   ├── ApplicationConfig.java
│   └── WebConfig.java
├── controller/
│   └── MathController.java
├── service/
│   └── MathService.java
├── model/
│   ├── MathRequest.java
│   ├── MathResponse.java
│   └── ErrorResponse.java
├── exception/
│   ├── MathException.java
│   └── GlobalExceptionHandler.java
└── util/
    └── MathValidator.java
```

### 6.2 Modelos de Dados

#### **MathRequest.java**

```java
package com.example.mathservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Modelo para requisições matemáticas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathRequest {
    
    @NotNull(message = "O primeiro operando não pode ser nulo")
    private BigDecimal operando1;
    
    @NotNull(message = "O segundo operando não pode ser nulo")
    private BigDecimal operando2;
}
```

#### **MathResponse.java**

```java
package com.example.mathservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Modelo para respostas matemáticas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MathResponse {
    
    private BigDecimal resultado;
    private String operacao;
    private BigDecimal operando1;
    private BigDecimal operando2;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    
    private String mensagem;
    private String statusCode;
}
```

#### **ErrorResponse.java**

```java
package com.example.mathservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo para respostas de erro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    
    private int status;
    private String erro;
    private String mensagem;
    private String caminhoRequisicao;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
```

### 6.3 Serviço de Operações Matemáticas

#### **MathService.java**

```java
package com.example.mathservice.service;

import com.example.mathservice.exception.MathException;
import com.example.mathservice.model.MathResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Serviço contendo lógica de operações matemáticas
 */
@Slf4j
@Service
public class MathService {

    private static final int PRECISION = 10;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * Realiza adição de dois números
     * @param operando1 Primeiro número
     * @param operando2 Segundo número
     * @return Resposta com resultado da adição
     */
    public MathResponse adicionar(BigDecimal operando1, BigDecimal operando2) {
        log.info("Executando adição: {} + {}", operando1, operando2);
        
        BigDecimal resultado = operando1.add(operando2);
        
        return buildResponse(resultado, "Adição", operando1, operando2, 
            "Adição realizada com sucesso");
    }

    /**
     * Realiza subtração de dois números
     * @param operando1 Minuendo
     * @param operando2 Subtraendo
     * @return Resposta com resultado da subtração
     */
    public MathResponse subtrair(BigDecimal operando1, BigDecimal operando2) {
        log.info("Executando subtração: {} - {}", operando1, operando2);
        
        BigDecimal resultado = operando1.subtract(operando2);
        
        return buildResponse(resultado, "Subtração", operando1, operando2,
            "Subtração realizada com sucesso");
    }

    /**
     * Realiza multiplicação de dois números
     * @param operando1 Primeiro multiplicando
     * @param operando2 Segundo multiplicando
     * @return Resposta com resultado da multiplicação
     */
    public MathResponse multiplicar(BigDecimal operando1, BigDecimal operando2) {
        log.info("Executando multiplicação: {} * {}", operando1, operando2);
        
        BigDecimal resultado = operando1.multiply(operando2);
        
        return buildResponse(resultado, "Multiplicação", operando1, operando2,
            "Multiplicação realizada com sucesso");
    }

    /**
     * Realiza divisão de dois números
     * @param operando1 Dividendo
     * @param operando2 Divisor
     * @return Resposta com resultado da divisão
     * @throws MathException se divisor for zero
     */
    public MathResponse dividir(BigDecimal operando1, BigDecimal operando2) {
        log.info("Executando divisão: {} / {}", operando1, operando2);
        
        if (operando2.compareTo(BigDecimal.ZERO) == 0) {
            log.error("Tentativa de divisão por zero");
            throw new MathException("Divisão por zero não é permitida");
        }
        
        BigDecimal resultado = operando1.divide(operando2, PRECISION, ROUNDING_MODE);
        
        return buildResponse(resultado, "Divisão", operando1, operando2,
            "Divisão realizada com sucesso");
    }

    /**
     * Calcula potência (operando1 elevado a operando2)
     * @param operando1 Base
     * @param operando2 Expoente
     * @return Resposta com resultado da potência
     * @throws MathException se expoente for negativo com base decimal
     */
    public MathResponse potencia(BigDecimal operando1, BigDecimal operando2) {
        log.info("Executando potência: {} ^ {}", operando1, operando2);
        
        // Validar se expoente é inteiro
        if (operando2.scale() > 0 && operando2.toBigInteger().doubleValue() != operando2.doubleValue()) {
            throw new MathException("Expoente deve ser um número inteiro");
        }
        
        int expoente = operando2.intValue();
        BigDecimal resultado = operando1.pow(expoente);
        
        return buildResponse(resultado, "Potência", operando1, operando2,
            "Potência realizada com sucesso");
    }

    /**
     * Constrói objeto de resposta padronizado
     */
    private MathResponse buildResponse(BigDecimal resultado, String operacao,
                                      BigDecimal op1, BigDecimal op2, String mensagem) {
        return MathResponse.builder()
            .resultado(resultado)
            .operacao(operacao)
            .operando1(op1)
            .operando2(op2)
            .timestamp(LocalDateTime.now())
            .mensagem(mensagem)
            .statusCode("200")
            .build();
    }
}
```

### 6.4 Controller REST

#### **MathController.java**

```java
package com.example.mathservice.controller;

import com.example.mathservice.model.MathRequest;
import com.example.mathservice.model.MathResponse;
import com.example.mathservice.service.MathService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para operações matemáticas
 * 
 * Endpoints:
 * - POST /api/math/adicionar
 * - POST /api/math/subtrair
 * - POST /api/math/multiplicar
 * - POST /api/math/dividir
 * - POST /api/math/potencia
 */
@Slf4j
@RestController
@RequestMapping("/api/math")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class MathController {

    private final MathService mathService;

    /**
     * Endpoint para adição
     * 
     * @param request Objeto contendo os dois operandos
     * @return ResponseEntity com resultado da adição
     * 
     * @example
     * POST /api/math/adicionar
     * Content-Type: application/json
     * {
     *   "operando1": 10.5,
     *   "operando2": 20.3
     * }
     * 
     * Response: 200 OK
     * {
     *   "resultado": 30.8,
     *   "operacao": "Adição",
     *   "operando1": 10.5,
     *   "operando2": 20.3,
     *   "timestamp": "2026-05-20T14:30:45",
     *   "mensagem": "Adição realizada com sucesso",
     *   "statusCode": "200"
     * }
     */
    @PostMapping("/adicionar")
    @RequestMapping(method = RequestMethod.POST, value = "/adicionar")
    public ResponseEntity<MathResponse> adicionar(@Valid @RequestBody MathRequest request) {
        log.info("Recebido requisição de adição");
        MathResponse response = mathService.adicionar(request.getOperando1(), request.getOperando2());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para subtração
     * 
     * @param request Objeto contendo os dois operandos
     * @return ResponseEntity com resultado da subtração
     * 
     * @example
     * POST /api/math/subtrair
     * {
     *   "operando1": 50,
     *   "operando2": 15
     * }
     * 
     * Response: 200 OK
     * {
     *   "resultado": 35,
     *   "operacao": "Subtração",
     *   ...
     * }
     */
    @PostMapping("/subtrair")
    @RequestMapping(method = RequestMethod.POST, value = "/subtrair")
    public ResponseEntity<MathResponse> subtrair(@Valid @RequestBody MathRequest request) {
        log.info("Recebido requisição de subtração");
        MathResponse response = mathService.subtrair(request.getOperando1(), request.getOperando2());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para multiplicação
     * 
     * @param request Objeto contendo os dois operandos
     * @return ResponseEntity com resultado da multiplicação
     * 
     * @example
     * POST /api/math/multiplicar
     * {
     *   "operando1": 5.5,
     *   "operando2": 4.2
     * }
     * 
     * Response: 200 OK
     * {
     *   "resultado": 23.1,
     *   "operacao": "Multiplicação",
     *   ...
     * }
     */
    @PostMapping("/multiplicar")
    @RequestMapping(method = RequestMethod.POST, value = "/multiplicar")
    public ResponseEntity<MathResponse> multiplicar(@Valid @RequestBody MathRequest request) {
        log.info("Recebido requisição de multiplicação");
        MathResponse response = mathService.multiplicar(request.getOperando1(), request.getOperando2());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para divisão
     * 
     * @param request Objeto contendo os dois operandos
     * @return ResponseEntity com resultado da divisão
     * @throws MathException se divisor for zero
     * 
     * @example
     * POST /api/math/dividir
     * {
     *   "operando1": 100,
     *   "operando2": 4
     * }
     * 
     * Response: 200 OK
     * {
     *   "resultado": 25,
     *   "operacao": "Divisão",
     *   ...
     * }
     * 
     * Error: 400 Bad Request
     * {
     *   "status": 400,
     *   "erro": "MathException",
     *   "mensagem": "Divisão por zero não é permitida",
     *   "caminhoRequisicao": "/api/math/dividir",
     *   "timestamp": "2026-05-20T14:31:20"
     * }
     */
    @PostMapping("/dividir")
    @RequestMapping(method = RequestMethod.POST, value = "/dividir")
    public ResponseEntity<MathResponse> dividir(@Valid @RequestBody MathRequest request) {
        log.info("Recebido requisição de divisão");
        MathResponse response = mathService.dividir(request.getOperando1(), request.getOperando2());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para potência
     * 
     * @param request Objeto contendo base e expoente
     * @return ResponseEntity com resultado da potência
     * 
     * @example
     * POST /api/math/potencia
     * {
     *   "operando1": 2,
     *   "operando2": 10
     * }
     * 
     * Response: 200 OK
     * {
     *   "resultado": 1024,
     *   "operacao": "Potência",
     *   ...
     * }
     */
    @PostMapping("/potencia")
    @RequestMapping(method = RequestMethod.POST, value = "/potencia")
    public ResponseEntity<MathResponse> potencia(@Valid @RequestBody MathRequest request) {
        log.info("Recebido requisição de potência");
        MathResponse response = mathService.potencia(request.getOperando1(), request.getOperando2());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint Health Check
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Math Service está funcionando!");
    }
}
```

### 6.5 Tratamento de Exceções Global

#### **MathException.java**

```java
package com.example.mathservice.exception;

/**
 * Exceção customizada para operações matemáticas
 */
public class MathException extends RuntimeException {
    
    public MathException(String message) {
        super(message);
    }
    
    public MathException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

#### **GlobalExceptionHandler.java**

```java
package com.example.mathservice.exception;

import com.example.mathservice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Manipulador global de exceções para toda aplicação
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de validação de argumentos
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        
        String mensagem = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .erro("Validation Error")
            .mensagem(mensagem)
            .caminhoRequisicao(request.getDescription(false).replace("uri=", ""))
            .timestamp(LocalDateTime.now())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções matemáticas customizadas
     */
    @ExceptionHandler(MathException.class)
    public ResponseEntity<ErrorResponse> handleMathException(
            MathException ex,
            WebRequest request) {
        
        log.error("MathException ocorreu: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .erro("Math Error")
            .mensagem(ex.getMessage())
            .caminhoRequisicao(request.getDescription(false).replace("uri=", ""))
            .timestamp(LocalDateTime.now())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata todas as exceções não tratadas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {
        
        log.error("Exceção inesperada: ", ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .erro("Internal Server Error")
            .mensagem("Ocorreu um erro interno no servidor")
            .caminhoRequisicao(request.getDescription(false).replace("uri=", ""))
            .timestamp(LocalDateTime.now())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### 6.6 Resumo da Organização REST

| Operação | Método | Endpoint | Request | Response |
|----------|--------|----------|---------|----------|
| **Adição** | POST | `/api/math/adicionar` | `{operando1, operando2}` | `{resultado, operacao, timestamp, ...}` |
| **Subtração** | POST | `/api/math/subtrair` | `{operando1, operando2}` | `{resultado, operacao, timestamp, ...}` |
| **Multiplicação** | POST | `/api/math/multiplicar` | `{operando1, operando2}` | `{resultado, operacao, timestamp, ...}` |
| **Divisão** | POST | `/api/math/dividir` | `{operando1, operando2}` | `{resultado, operacao, timestamp, ...}` |
| **Potência** | POST | `/api/math/potencia` | `{operando1, operando2}` | `{resultado, operacao, timestamp, ...}` |
| **Health** | GET | `/api/math/health` | - | String status |

---

## 7. Testes e Validação

### 7.1 Testes Unitários

#### **MathServiceTest.java**

```java
package com.example.mathservice.service;

import com.example.mathservice.exception.MathException;
import com.example.mathservice.model.MathResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do Serviço Matemático")
class MathServiceTest {

    @InjectMocks
    private MathService mathService;

    private BigDecimal operando1;
    private BigDecimal operando2;

    @BeforeEach
    void setUp() {
        operando1 = new BigDecimal("10.5");
        operando2 = new BigDecimal("5.5");
    }

    @Test
    @DisplayName("Deve realizar adição corretamente")
    void testAdicionar() {
        // When
        MathResponse response = mathService.adicionar(operando1, operando2);

        // Then
        assertEquals(new BigDecimal("16.0"), response.getResultado());
        assertEquals("Adição", response.getOperacao());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Deve realizar subtração corretamente")
    void testSubtrair() {
        // When
        MathResponse response = mathService.subtrair(operando1, operando2);

        // Then
        assertEquals(new BigDecimal("5.0"), response.getResultado());
        assertEquals("Subtração", response.getOperacao());
    }

    @Test
    @DisplayName("Deve realizar multiplicação corretamente")
    void testMultiplicar() {
        // When
        MathResponse response = mathService.multiplicar(operando1, operando2);

        // Then
        assertEquals(new BigDecimal("57.75"), response.getResultado());
        assertEquals("Multiplicação", response.getOperacao());
    }

    @Test
    @DisplayName("Deve realizar divisão corretamente")
    void testDividir() {
        // When
        MathResponse response = mathService.dividir(
            new BigDecimal("100"),
            new BigDecimal("4")
        );

        // Then
        assertEquals(new BigDecimal("25"), response.getResultado());
        assertEquals("Divisão", response.getOperacao());
    }

    @Test
    @DisplayName("Deve lançar exceção em divisão por zero")
    void testDividirPorZero() {
        // When & Then
        assertThrows(MathException.class, () -> {
            mathService.dividir(operando1, BigDecimal.ZERO);
        });
    }

    @Test
    @DisplayName("Deve realizar potência corretamente")
    void testPotencia() {
        // When
        MathResponse response = mathService.potencia(
            new BigDecimal("2"),
            new BigDecimal("10")
        );

        // Then
        assertEquals(new BigDecimal("1024"), response.getResultado());
        assertEquals("Potência", response.getOperacao());
    }
}
```

### 7.2 Testes de Integração

#### **MathControllerIntegrationTest.java**

```java
package com.example.mathservice.controller;

import com.example.mathservice.model.MathRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de Integração do Controller")
class MathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 200 OK para endpoint de adição")
    void testAdicionarEndpoint() throws Exception {
        MathRequest request = new MathRequest(
            new BigDecimal("10"),
            new BigDecimal("5")
        );

        mockMvc.perform(post("/api/math/adicionar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultado").value(15))
            .andExpect(jsonPath("$.operacao").value("Adição"));
    }

    @Test
    @DisplayName("Deve retornar erro 400 para requisição inválida")
    void testInvalidRequest() throws Exception {
        String invalidJson = "{ \"operando1\": null }";

        mockMvc.perform(post("/api/math/adicionar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar erro ao dividir por zero")
    void testDivisaoPorZero() throws Exception {
        MathRequest request = new MathRequest(
            new BigDecimal("10"),
            new BigDecimal("0")
        );

        mockMvc.perform(post("/api/math/dividir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.erro").value("Math Error"));
    }

    @Test
    @DisplayName("Deve retornar status 200 para health check")
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/math/health"))
            .andExpect(status().isOk())
            .andExpect(content().string("Math Service está funcionando!"));
    }
}
```

### 7.3 Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn test jacoco:report

# Executar teste específico
mvn -Dtest=MathServiceTest test

# Executar com modo verbose
mvn test -X

# Gerar relatório de testes
mvn surefire-report:report
```

---

## 8. Repositório Git

### 8.1 Inicialização e Setup

```bash
# Navegue para o diretório do projeto
cd /path/to/math-service

# Inicializar repositório Git (se não inicializado)
git init

# Configurar dados do desenvolvedor
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"

# Verificar configuração
git config --list
```

### 8.2 Estrutura do Repositório

```
math-service/
├── .git/
├── .gitignore
├── .github/
│   └── workflows/
│       └── ci-cd.yml
├── src/
├── pom.xml
├── README.md
├── LICENSE
├── CONTRIBUTING.md
└── RELATORIO_SPRING_BOOT.md (este arquivo)
```

### 8.3 Arquivo .gitignore

```
# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
.mvn/

# IDE
.idea/
*.iml
*.iws
*.ipr
.vscode/
*.code-workspace
.DS_Store

# Logs
logs/
*.log

# Arquivos de build
*.class
.gradle/
build/

# Environment
.env
.env.local
.env.*.local
```

### 8.4 Commits Iniciais

```bash
# Adicionar todos os arquivos
git add .

# Commit inicial
git commit -m "feat: inicializar projeto Spring Boot com endpoints REST"

# Commit da estrutura
git commit -m "refactor: organizar estrutura de pacotes e dependências"

# Commit dos controllers
git commit -m "feat: implementar 5 serviços REST para operações matemáticas"

# Commit dos testes
git commit -m "test: adicionar testes unitários e de integração"

# Commit da documentação
git commit -m "docs: adicionar relatório e documentação completa"
```

### 8.5 GitHub Setup (Exemplo)

```bash
# Criar repositório no GitHub e copiar URL SSH
# Exemplo: git@github.com:usuario/math-service.git

# Adicionar remote
git remote add origin git@github.com:usuario/math-service.git

# Verificar remote
git remote -v

# Fazer push para GitHub
git branch -M main
git push -u origin main

# Para pushes subsequentes
git push
```

### 8.6 Estrutura de Branches

```bash
# Branch main (produção)
git checkout -b main

# Branch develop (desenvolvimento)
git checkout -b develop

# Branches de feature
git checkout -b feature/adicionar-cache
git checkout -b feature/melhorar-validacao

# Branches de bugfix
git checkout -b bugfix/corrigir-divisao-zero

# Branches de release
git checkout -b release/1.1.0
```

### 8.7 Fluxo de Trabalho Recomendado

```bash
# 1. Atualizar branch develop
git checkout develop
git pull origin develop

# 2. Criar branch de feature
git checkout -b feature/minha-feature

# 3. Fazer commits
git add .
git commit -m "feat: descrição clara da mudança"

# 4. Push para remote
git push origin feature/minha-feature

# 5. Criar Pull Request no GitHub

# 6. Após aprovação, merge em develop
git checkout develop
git merge feature/minha-feature

# 7. Deletar branch local
git branch -d feature/minha-feature

# 8. Deletar branch remoto
git push origin --delete feature/minha-feature
```

### 8.8 Concessão de Acesso

Para conceder acesso a professores e monitores no GitHub:

1. **Repositório Privado:**
   - Settings → Collaborators
   - Adicionar usernames do GitHub dos professores

2. **Repositório Público:**
   - Fornecer link do repositório
   - Qualquer pessoa pode clonar e revisar

**Recomendação:** Usar repositório privado com acesso concedido especificamente aos avaliadores.

---

## Resumo Executivo

### Decisões Técnicas Justificadas

1. **Maven como Ferramenta de Build**
   - Facilita resolução e correção de erros
   - Mais acessível para iniciantes
   - Excelente integração com Spring Boot

2. **Spring Boot 3.2 LTS**
   - Suporte de longo prazo
   - Java 17 moderno e suportado
   - Autoconfiguração robusta

3. **Arquitetura em Camadas**
   - Controller → Service → Model
   - Separação de responsabilidades clara
   - Fácil manutenção e testes

4. **BigDecimal para Operações Matemáticas**
   - Precisão arbitrária
   - Evita erros de ponto flutuante
   - Adequado para cálculos financeiros

5. **Tratamento Global de Exceções**
   - Respostas padronizadas
   - Melhor experiência do cliente API
   - Logging centralizado

### Benefícios da Implementação

✅ **Robustez:** Tratamento completo de erros  
✅ **Escalabilidade:** Estrutura extensível  
✅ **Testabilidade:** Cobertura completa de testes  
✅ **Manutenibilidade:** Código limpo e documentado  
✅ **Profissionalismo:** Segue padrões da indústria  

---

## Próximos Passos (Melhorias Futuras)

1. **Autenticação e Autorização**
   - Spring Security
   - JWT tokens

2. **Cache**
   - Spring Cache com Redis
   - Invalidação inteligente

3. **Documentação Automática**
   - Springdoc OpenAPI
   - Swagger UI

4. **Monitoria e Logging**
   - ELK Stack
   - Spring Cloud Sleuth

5. **Containerização**
   - Docker
   - Docker Compose

6. **CI/CD**
   - GitHub Actions
   - Deploy automático

---

## Referências

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Maven Official Guide](https://maven.apache.org/guides/)
- [Spring Initializr](https://start.spring.io/)
- [Spring Boot CLI Documentation](https://spring.io/projects/spring-boot#learn)
- [RESTful API Best Practices](https://restfulapi.net/)
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)

---

**Relatório Elaborado:** 20 de maio de 2026  
**Status:** Completo e Pronto para Entrega  
**Versão:** 1.0.0
