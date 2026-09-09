package org.example.medschedule.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Usuario medico;

    @ManyToOne
    private Especialidade especialidade;

    private String dataHora;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    public Consulta() {
    }

    public Consulta(Paciente paciente, Usuario medico, Especialidade especialidade, String dataHora, StatusConsulta status) {
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = especialidade;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getMedico() {
        return this.medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Especialidade getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
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
