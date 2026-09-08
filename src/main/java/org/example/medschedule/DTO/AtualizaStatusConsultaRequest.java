package org.example.medschedule.DTO;

import org.example.medschedule.entities.StatusConsulta;

public class AtualizaStatusConsultaRequest {

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
