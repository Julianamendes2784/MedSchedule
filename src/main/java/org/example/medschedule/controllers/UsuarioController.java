package org.example.medschedule.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public String getUsuarios() {
        return "Hello World from UsuarioController!";
    }

    @GetMapping("/{id}")
    public String consultaPorId(@PathVariable Long id) {
        return "Usuarios por ID: " + id;
    }

    @GetMapping("/especialidade/{especialidadeId}")
    public String consultaUsuarioPorEspecialidade(@PathVariable Long especialidadeId) {
        return "Usuarios por Especialidade: " + especialidadeId;
    }
}
