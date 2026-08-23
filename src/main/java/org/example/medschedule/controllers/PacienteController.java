package org.example.medschedule.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

