package org.example.medschedule.controllers;

import org.example.medschedule.entities.Especialidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @GetMapping
    public String getEspecialidades() {
        return "Hello World from EspecialidadeController!";
    }

    @GetMapping("/{id}")
    public String consultaPorId(@PathVariable Long id) {
        return "Especialidade por ID: " + id;
    }

    @GetMapping("/medico/{medicoId}")
    public String consultaEspecialidadePorMedico(@PathVariable Long medicoId) {
        return "Especialidade por Medico: " + medicoId;
    }

    @PostMapping
    public ResponseEntity<Especialidade> CadastrarEspecialidade(@RequestBody Especialidade especialidadeRequest) {
        return ResponseEntity.ok(especialidadeRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> AtualizarEspecialidade(@PathVariable Long id, @RequestBody Especialidade especialidadeRequest) {
        especialidadeRequest.setId(id);
        return ResponseEntity.ok(especialidadeRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Especialidade> AtualizarParcialEspecialidade(@PathVariable Long id, @RequestBody Especialidade especialidadeRequest) {
        especialidadeRequest.setId(id);
        return ResponseEntity.ok(especialidadeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarEspecialidade(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
