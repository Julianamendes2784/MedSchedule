package org.example.medschedule.dto;

/**
 * DTO de ENTRADA para agendar/reagendar uma Consulta. Em vez de objetos completos,
 * o cliente envia apenas os ids de paciente, médico e especialidade, mais a data/hora.
 */
public class ConsultaRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON recebido neste objeto.
    public ConsultaRequest() {
    }

    // Id do paciente que será atendido.
    private Long pacienteId;

    // Id do médico (Usuario) que fará o atendimento.
    private Long medicoId;

    // Id da especialidade da consulta.
    private Long especialidadeId;

    private String dataHora;

    // ---- Getters e setters (encapsulamento) ----

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
