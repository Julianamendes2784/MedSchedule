package org.example.medschedule.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Entidade Usuario: representa quem usa o sistema (no domínio da clínica, o médico
 * que atende as consultas). É mapeada para a tabela "usuario" no PostgreSQL.
 * Também guarda a senha usada no endpoint de login.
 */
@Entity
public class Usuario {

    // Construtor vazio: obrigatório para o JPA/Hibernate instanciar a entidade.
    public Usuario(){

    }

    // Construtor de conveniência com os dados principais.
    public Usuario(String nome, String cpf, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Chave primária, gerada automaticamente pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    // O CPF é usado como "login" no endpoint /login.
    private String cpf;

    private String dataNascimento;

    // Status lógico: "A" = ativo, "D" = deletado (exclusão lógica).
    private String status;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    private String senha;

    // ---- Getters e setters (encapsulamento dos atributos privados) ----

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
