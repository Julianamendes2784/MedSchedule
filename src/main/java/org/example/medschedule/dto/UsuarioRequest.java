package org.example.medschedule.dto;

/**
 * DTO de ENTRADA para cadastrar/atualizar um Usuario: o JSON enviado no corpo do POST/PUT.
 */
public class UsuarioRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON recebido neste objeto.
    public UsuarioRequest() {
    }

    private String nome;

    private String cpf;

    private String dataNascimento;

    private String senha;

    // ---- Getters e setters (encapsulamento) ----

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
