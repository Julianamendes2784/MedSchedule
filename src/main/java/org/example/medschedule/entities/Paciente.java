package org.example.medschedule.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Entidade Paciente: representa uma pessoa atendida na clínica.
 *
 * @Entity faz o Hibernate (ORM) mapear esta classe para uma tabela real no PostgreSQL
 * ("paciente"): cada atributo vira uma coluna e cada objeto vira uma linha.
 * Os atributos são privados (encapsulamento) e só são acessados por getters/setters.
 */
@Entity
public class Paciente {

    // Chave primária da tabela.
    // IDENTITY = o próprio banco gera o valor automaticamente (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String dataNascimento;

    // Situação do registro: nasce "A" (ativo) no cadastro e pode ser alterada pela rota PATCH /status.
    private String status;

    // Quando o registro foi criado.
    private LocalDateTime dataCadastro;

    // Quando o registro foi alterado pela última vez.
    private LocalDateTime dataAtualizacao;

    // Construtor vazio: obrigatório para o JPA/Hibernate conseguir instanciar a entidade.
    public Paciente() {
    }

    // Construtor de conveniência para criar um Paciente já com os dados principais.
    public Paciente(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // ---- Getters e setters: forma controlada de ler/alterar os atributos privados (encapsulamento) ----

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

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
