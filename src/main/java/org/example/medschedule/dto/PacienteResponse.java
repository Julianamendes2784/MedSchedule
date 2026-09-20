package org.example.medschedule.dto;

/**
 * DTO de SAÍDA das operações de escrita em Paciente: devolve ao cliente o id do registro
 * afetado e uma mensagem de confirmação (em vez de devolver a entidade inteira).
 */
public class PacienteResponse {

    // Construtor vazio: necessário para o Jackson serializar/desserializar o objeto.
    public PacienteResponse() {
    }

    // Construtor usado pelo controller para montar a resposta já preenchida.
    public PacienteResponse(Long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    private Long id;

    private String mensagem;

    // ---- Getters e setters (encapsulamento) ----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
