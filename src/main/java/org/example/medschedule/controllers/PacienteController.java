package org.example.medschedule.controllers;

import org.example.medschedule.entities.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Paciente> CadastrarPaciente(@RequestBody Paciente pacienteRequest) {
        return ResponseEntity.ok(pacienteRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> AtualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteRequest) {
        pacienteRequest.setId(id);
        return ResponseEntity.ok(pacienteRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> AtualizarParcialPaciente(@PathVariable Long id, @RequestBody Paciente pacienteRequest) {
        pacienteRequest.setId(id);
        return ResponseEntity.ok(pacienteRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarPaciente(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
