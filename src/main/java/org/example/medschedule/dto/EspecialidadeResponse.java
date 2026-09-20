package org.example.medschedule.dto;

/**
 * DTO de SAÍDA das operações de escrita em Especialidade: id do registro afetado + mensagem de confirmação.
 */
public class EspecialidadeResponse {

    // Construtor vazio: necessário para o Jackson serializar/desserializar o objeto.
    public EspecialidadeResponse() {
    }

    // Construtor usado pelo controller para montar a resposta já preenchida.
    public EspecialidadeResponse(Long id, String mensagem) {
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
