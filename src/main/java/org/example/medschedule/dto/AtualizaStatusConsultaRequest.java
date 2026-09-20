package org.example.medschedule.dto;

import org.example.medschedule.entities.StatusConsulta;

/**
 * DTO de ENTRADA do PATCH /consultas/{id}/status: carrega somente o novo status.
 * Por ser um enum, só aceita AGENDADA, REALIZADA ou CANCELADA (outro valor gera erro 400).
 */
public class AtualizaStatusConsultaRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON neste objeto.
    public AtualizaStatusConsultaRequest() {
    }

    private StatusConsulta status;

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }
}
