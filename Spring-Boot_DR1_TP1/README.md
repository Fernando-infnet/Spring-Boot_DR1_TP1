# Relatório: Desenvolvimento de Aplicação Spring Boot com Serviços REST

**Status:** Documentação Completa  
**Ferramenta de Construção:** Maven

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

### 6.2 Resumo da Organização REST

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

### 7.1 Executar Testes

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

## 8. Prints de Execução

<img width="1049" height="1089" alt="run1" src="https://github.com/user-attachments/assets/fff90864-12d2-4763-9d72-894ab84511fd" />

<img width="1503" height="279" alt="run2" src="https://github.com/user-attachments/assets/c62e6c3a-128e-495c-b8e3-9e9b6363a761" />

<img width="1200" height="259" alt="test" src="https://github.com/user-attachments/assets/8fbb3bed-6723-478c-aeaf-732c44cdf7b1" />

<img width="441" height="392" alt="operations0 5" src="https://github.com/user-attachments/assets/92bce21a-e34e-481b-908b-188ebba449cd" />

<img width="436" height="864" alt="operations" src="https://github.com/user-attachments/assets/03659524-853f-43d1-91fa-467109443529" />


---

