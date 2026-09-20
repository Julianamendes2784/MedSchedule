package org.example.medschedule.dto;

/**
 * DTO de ENTRADA do PATCH /pacientes/{id}/status: carrega somente o novo status,
 * já que essa rota altera apenas esse campo.
 */
public class AtualizaStatusPacienteRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON neste objeto.
    public AtualizaStatusPacienteRequest() {
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
