package org.example.medschedule.dto;

/**
 * DTO de ENTRADA do PATCH /usuarios/{id}/status: carrega somente o novo status.
 */
public class AtualizaStatusUsuarioRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON neste objeto.
    public AtualizaStatusUsuarioRequest() {
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
