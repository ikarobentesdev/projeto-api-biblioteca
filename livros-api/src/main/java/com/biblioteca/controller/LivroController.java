package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Camada de Controller: expõe os endpoints REST da API.
 * Responsabilidade: receber requisições HTTP e devolver respostas, basicamente.
 *
 * Comunicação: Controller → Service → Repository
 * 
 * se não entender tranca o curso 
 */
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    // Injeção de dependência via construtor
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // ── Endpoint 1: CADASTRAR ─────────────────────────────────────────────────
    /**
     * POST /api/livros
     * Cadastra um novo livro no sistema.
     * Retorna 201 Created com o livro salvo.
     */
    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        try {
            Livro novoLivro = livroService.cadastrar(livro);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ── Endpoint 2: LISTAR ────────────────────────────────────────────────────
    /**
     * GET /api/livros
     * Retorna a lista de todos os livros cadastrados.
     * Retorna 200 OK com a lista (pode estar vazia).
     */
    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    // ── Endpoint 3: BUSCAR POR ID ─────────────────────────────────────────────
    /**
     * GET /api/livros/{id}
     * Busca um livro específico pelo seu ID.
     * Retorna 200 OK com o livro ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        try {
            Livro livro = livroService.buscarPorId(id);
            return ResponseEntity.ok(livro);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Endpoint 4: REMOVER ───────────────────────────────────────────────────
    /**
     * DELETE /api/livros/{id}
     * Remove um livro pelo seu ID.
     * Retorna 204 No Content ou 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            livroService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
