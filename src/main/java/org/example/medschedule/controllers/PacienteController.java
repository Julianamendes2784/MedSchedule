package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusPacienteRequest;
import org.example.medschedule.DTO.PacienteRequest;
import org.example.medschedule.DTO.PacienteResponse;
import org.example.medschedule.entities.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @GetMapping
    public String getPacientes() {
        return "Hello World from PacienteController!";
    }

    @GetMapping("/{id}")
    public String consultaPorId(@PathVariable Long id) {
        return "Pacientes por ID: " + id;
    }

    @GetMapping("/medico/{medicoId}")
    public String consultaPacientesPorMedico(@PathVariable Long medicoId) {
        return "Pacientes por Medico: " + medicoId;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> CadastrarPaciente(@RequestBody PacienteRequest pacienteRequest) {
        Paciente pacienteBanco = new Paciente();
        pacienteBanco.setNome(pacienteRequest.getNome());
        pacienteBanco.setCpf(pacienteRequest.getCpf());
        pacienteBanco.setTelefone(pacienteRequest.getTelefone());
        pacienteBanco.setDataNascimento(pacienteRequest.getDataNascimento());

        pacienteBanco.setDataCadastro(LocalDateTime.now());
        pacienteBanco.setStatus("A");

        return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> AtualizarPaciente(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest) {
        //Consulta no banco
        Paciente pacienteBanco = new Paciente();

        if (pacienteBanco != null) {
            pacienteBanco.setNome(pacienteRequest.getNome());
            pacienteBanco.setCpf(pacienteRequest.getCpf());
            pacienteBanco.setTelefone(pacienteRequest.getTelefone());
            pacienteBanco.setDataNascimento(pacienteRequest.getDataNascimento());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Paciente atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PacienteResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusPacienteRequest pacienteRequest) {
        //Consulta no banco
        Paciente pacienteBanco = new Paciente();

        if (pacienteBanco != null) {
            pacienteBanco.setStatus(pacienteRequest.getStatus());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> DeletarPaciente(@PathVariable Long id) {
        //Consulta no banco
        Paciente pacienteBanco = new Paciente();

        if (pacienteBanco != null) {
            pacienteBanco.setStatus("D");
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
