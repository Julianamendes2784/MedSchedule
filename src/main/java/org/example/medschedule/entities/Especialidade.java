package org.example.medschedule.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Entidade Especialidade: representa uma especialidade médica (ex.: Cardiologia).
 * É mapeada para a tabela "especialidade" no PostgreSQL.
 */
@Entity
public class Especialidade {

    // Chave primária, gerada automaticamente pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Situação do registro: nasce "A" (ativo) no cadastro e pode ser alterada pela rota PATCH /status.
    private String status;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    // Construtor vazio: obrigatório para o JPA/Hibernate instanciar a entidade.
    public Especialidade() {
    }

    // Construtor de conveniência.
    public Especialidade(String nome) {
        this.nome = nome;
    }

    // ---- Getters e setters (encapsulamento dos atributos privados) ----

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
