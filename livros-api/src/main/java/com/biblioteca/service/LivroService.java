package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de Serviço: contém as regras de negócio do sistema.
 * Responsabilidade: orquestrar operações e aplicar validações.
 *
 * Injeção de dependência via construtor (boa prática recomendada).
 */
@Service
public class LivroService {

    // Dependência injetada pelo Spring via construtor
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // ── Regras de negócio ─────────────────────────────────────────────────────

    /**
     * Cadastra um novo livro após validar os dados obrigatórios.
     */
    public Livro cadastrar(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título do livro é obrigatório.");
        }
        if (livro.getAutor() == null || livro.getAutor().isBlank()) {
            throw new IllegalArgumentException("O autor do livro é obrigatório.");
        }
        return livroRepository.salvar(livro);
    }

    /**
     * Retorna a lista completa de livros cadastrados.
     */
    public List<Livro> listarTodos() {
        return livroRepository.buscarTodos();
    }

    /**
     * Busca um livro pelo ID. Lança exceção se não encontrado.
     */
    public Livro buscarPorId(Long id) {
        Optional<Livro> livro = livroRepository.buscarPorId(id);
        return livro.orElseThrow(() ->
                new RuntimeException("Livro com ID " + id + " não encontrado."));
    }

    /**
     * Remove um livro pelo ID. Lança exceção se não encontrado.
     */
    public void remover(Long id) {
        boolean removido = livroRepository.remover(id);
        if (!removido) {
            throw new RuntimeException("Livro com ID " + id + " não encontrado.");
        }
    }
}
