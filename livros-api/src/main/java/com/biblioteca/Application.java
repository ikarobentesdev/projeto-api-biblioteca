package com.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação Spring Boot.
 *
 * A anotação @SpringBootApplication combina:
 *   - @Configuration    → define a classe como fonte de beans
 *   - @EnableAutoConfiguration → configura automaticamente o Spring
 *   - @ComponentScan    → escaneia os pacotes em busca de componentes
 *                         (@Controller, @Service, @Repository, etc.)
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("======================================");
        System.out.println("  API de Livros iniciada com sucesso!");
        System.out.println("  Acesse: http://localhost:8080/api/livros");
        System.out.println("======================================");
    }
}
