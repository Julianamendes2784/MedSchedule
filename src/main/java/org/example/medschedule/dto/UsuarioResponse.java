package org.example.medschedule.dto;

/**
 * DTO de SAÍDA das operações de escrita em Usuario: id do registro afetado + mensagem de confirmação.
 */
public class UsuarioResponse {

    // Construtor vazio: necessário para o Jackson serializar/desserializar o objeto.
    public UsuarioResponse() {
    }

    // Construtor usado pelo controller para montar a resposta já preenchida.
    public UsuarioResponse(Long id, String mensagem) {
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
