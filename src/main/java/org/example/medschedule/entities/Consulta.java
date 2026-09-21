package org.example.medschedule.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Entidade Consulta: representa um agendamento de um paciente com um médico e uma especialidade
 * em uma data/hora. É mapeada para a tabela "consulta" no PostgreSQL.
 *
 * Nesta fase NÃO há relacionamentos entre tabelas (sem @ManyToOne/chave estrangeira):
 * paciente, médico e especialidade são guardados apenas como ids em colunas simples.
 */
@Entity
public class Consulta {

    // Chave primária, gerada automaticamente pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Id do paciente atendido (coluna simples "paciente_id", sem chave estrangeira).
    private Long pacienteId;

    // Id do médico (Usuario) que fará o atendimento (coluna "medico_id").
    private Long medicoId;

    // Id da especialidade da consulta (coluna "especialidade_id").
    private Long especialidadeId;

    private String dataHora;

    // Guarda o enum como TEXTO ("AGENDADA", "REALIZADA", "CANCELADA") no banco,
    // em vez do número da posição, o que evita quebrar dados se a ordem do enum mudar.
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    // Construtor vazio: obrigatório para o JPA/Hibernate instanciar a entidade.
    public Consulta() {
    }

    // Construtor de conveniência com os dados principais.
    public Consulta(Long pacienteId, Long medicoId, Long especialidadeId, String dataHora, StatusConsulta status) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.especialidadeId = especialidadeId;
        this.dataHora = dataHora;
        this.status = status;
    }

    // ---- Getters e setters (encapsulamento dos atributos privados) ----

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return this.pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return this.medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getEspecialidadeId() {
        return this.especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public String getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public StatusConsulta getStatus() {
        return this.status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
