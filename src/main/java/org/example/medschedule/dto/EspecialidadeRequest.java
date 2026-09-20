package org.example.medschedule.dto;

/**
 * DTO de ENTRADA para cadastrar/atualizar uma Especialidade: o JSON enviado no corpo do POST/PUT.
 */
public class EspecialidadeRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON recebido neste objeto.
    public EspecialidadeRequest() {
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
