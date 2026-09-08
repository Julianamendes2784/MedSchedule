package org.example.medschedule.controllers;

import org.example.medschedule.entities.Consulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Consulta> CadastrarConsulta(@RequestBody Consulta consultaRequest) {
        return ResponseEntity.ok(consultaRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> AtualizarConsulta(@PathVariable Long id, @RequestBody Consulta consultaRequest) {
        consultaRequest.setId(id);
        return ResponseEntity.ok(consultaRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Consulta> AtualizarParcialConsulta(@PathVariable Long id, @RequestBody Consulta consultaRequest) {
        consultaRequest.setId(id);
        return ResponseEntity.ok(consultaRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarConsulta(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
