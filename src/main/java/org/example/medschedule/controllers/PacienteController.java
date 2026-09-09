package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusPacienteRequest;
import org.example.medschedule.DTO.PacienteRequest;
import org.example.medschedule.DTO.PacienteResponse;
import org.example.medschedule.entities.Paciente;
import org.example.medschedule.repositories.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultaPorId(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

        pacienteBanco = pacienteRepository.save(pacienteBanco);

        return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> AtualizarPaciente(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest) {
        Paciente pacienteBanco = pacienteRepository.findById(id).orElse(null);

        if (pacienteBanco != null) {
            pacienteBanco.setNome(pacienteRequest.getNome());
            pacienteBanco.setCpf(pacienteRequest.getCpf());
            pacienteBanco.setTelefone(pacienteRequest.getTelefone());
            pacienteBanco.setDataNascimento(pacienteRequest.getDataNascimento());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());
            pacienteRepository.save(pacienteBanco);

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Paciente atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PacienteResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusPacienteRequest pacienteRequest) {
        Paciente pacienteBanco = pacienteRepository.findById(id).orElse(null);

        if (pacienteBanco != null) {
            pacienteBanco.setStatus(pacienteRequest.getStatus());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());
            pacienteRepository.save(pacienteBanco);

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> DeletarPaciente(@PathVariable Long id) {
        Paciente pacienteBanco = pacienteRepository.findById(id).orElse(null);

        if (pacienteBanco != null) {
            pacienteBanco.setStatus("D");
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());
            pacienteRepository.save(pacienteBanco);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
