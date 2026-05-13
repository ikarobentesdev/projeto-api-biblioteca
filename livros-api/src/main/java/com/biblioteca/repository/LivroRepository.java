package com.biblioteca.repository;

import com.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositório simulado em memória.
 * Responsabilidade: persistência (CRUD básico) dos livros.
 */
@Repository
public class LivroRepository {

    // Simula o banco de dados em memória
    private final List<Livro> baseDeDados = new ArrayList<>();

    // Gerador de IDs auto-incrementais (thread-safe)
    private final AtomicLong contadorId = new AtomicLong(1);

    // ── Inicialização com dados de exemplo ────────────────────────────────────

    public LivroRepository() {
        baseDeDados.add(new Livro(contadorId.getAndIncrement(),
                "Clean Code", "Robert C. Martin", "978-0132350884", 2008));
        baseDeDados.add(new Livro(contadorId.getAndIncrement(),
                "O Programador Pragmático", "David Thomas", "978-8577807481", 1999));
        baseDeDados.add(new Livro(contadorId.getAndIncrement(),
                "Design Patterns", "Gang of Four", "978-0201633610", 1994));
    }

    // ── Métodos do repositório ────────────────────────────────────────────────

    /**
     * Salva um novo livro, atribuindo um ID automático.
     */
    public Livro salvar(Livro livro) {
        livro.setId(contadorId.getAndIncrement());
        baseDeDados.add(livro);
        return livro;
    }

    /**
     * Retorna todos os livros cadastrados.
     */
    public List<Livro> buscarTodos() {
        return new ArrayList<>(baseDeDados);
    }

    /**
     * Busca um livro pelo seu ID.
     */
    public Optional<Livro> buscarPorId(Long id) {
        return baseDeDados.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst();
    }

    /**
     * Remove um livro pelo seu ID.
     * Retorna true se encontrado e removido, false caso contrário.
     */
    public boolean remover(Long id) {
        return baseDeDados.removeIf(livro -> livro.getId().equals(id));
    }
}
