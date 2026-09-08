package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusConsultaRequest;
import org.example.medschedule.DTO.ConsultaRequest;
import org.example.medschedule.DTO.ConsultaResponse;
import org.example.medschedule.entities.Consulta;
import org.example.medschedule.entities.StatusConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @GetMapping
    public String getConsultas() {
        return "Hello World from ConsultaController!";
    }

    @GetMapping("/{id}")
    public String consultaPorId(@PathVariable Long id) {
        return "Consulta por ID: " + id;
    }

    @GetMapping("/medico/{medicoId}")
    public String consultaConsultasPorMedico(@PathVariable Long medicoId) {
        return "Consultas por Medico: " + medicoId;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> CadastrarConsulta(@RequestBody ConsultaRequest consultaRequest) {
        Consulta consultaBanco = new Consulta();
        consultaBanco.setDataHora(consultaRequest.getDataHora());

        consultaBanco.setDataCadastro(LocalDateTime.now());
        consultaBanco.setStatus(StatusConsulta.AGENDADA);

        return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> AtualizarConsulta(@PathVariable Long id, @RequestBody ConsultaRequest consultaRequest) {
        //Consulta no banco
        Consulta consultaBanco = new Consulta();

        if (consultaBanco != null) {
            consultaBanco.setDataHora(consultaRequest.getDataHora());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Consulta atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ConsultaResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusConsultaRequest consultaRequest) {
        //Consulta no banco
        Consulta consultaBanco = new Consulta();

        if (consultaBanco != null) {
            consultaBanco.setStatus(consultaRequest.getStatus());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponse> DeletarConsulta(@PathVariable Long id) {
        //Consulta no banco
        Consulta consultaBanco = new Consulta();

        if (consultaBanco != null) {
            consultaBanco.setStatus(StatusConsulta.CANCELADA);
            consultaBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
