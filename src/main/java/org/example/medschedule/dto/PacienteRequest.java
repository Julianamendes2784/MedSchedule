package org.example.medschedule.dto;

/**
 * DTO (Data Transfer Object) de ENTRADA para cadastrar/atualizar um Paciente.
 * Representa exatamente o JSON que o cliente envia no corpo do POST/PUT, sem expor
 * campos internos da entidade (id, status, datas), que são controlados pelo servidor.
 */
public class PacienteRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON recebido neste objeto.
    public PacienteRequest() {
    }

    private String nome;

    private String cpf;

    private String telefone;

    private String dataNascimento;

    // ---- Getters e setters (encapsulamento) ----

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
