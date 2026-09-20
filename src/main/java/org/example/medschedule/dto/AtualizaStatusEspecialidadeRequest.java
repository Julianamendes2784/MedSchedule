package org.example.medschedule.dto;

/**
 * DTO de ENTRADA do PATCH /especialidades/{id}/status: carrega somente o novo status.
 */
public class AtualizaStatusEspecialidadeRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON neste objeto.
    public AtualizaStatusEspecialidadeRequest() {
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
