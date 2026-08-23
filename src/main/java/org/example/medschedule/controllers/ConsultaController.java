package org.example.medschedule.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
