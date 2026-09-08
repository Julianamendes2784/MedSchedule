package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusEspecialidadeRequest;
import org.example.medschedule.DTO.EspecialidadeRequest;
import org.example.medschedule.DTO.EspecialidadeResponse;
import org.example.medschedule.entities.Especialidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public ResponseEntity<EspecialidadeResponse> CadastrarEspecialidade(@RequestBody EspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = new Especialidade();
        especialidadeBanco.setNome(especialidadeRequest.getNome());

        especialidadeBanco.setDataCadastro(LocalDateTime.now());
        especialidadeBanco.setStatus("A");

        return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> AtualizarEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeRequest especialidadeRequest) {
        //Consulta no banco
        Especialidade especialidadeBanco = new Especialidade();

        if (especialidadeBanco != null) {
            especialidadeBanco.setNome(especialidadeRequest.getNome());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Especialidade atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EspecialidadeResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusEspecialidadeRequest especialidadeRequest) {
        //Consulta no banco
        Especialidade especialidadeBanco = new Especialidade();

        if (especialidadeBanco != null) {
            especialidadeBanco.setStatus(especialidadeRequest.getStatus());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> DeletarEspecialidade(@PathVariable Long id) {
        //Consulta no banco
        Especialidade especialidadeBanco = new Especialidade();

        if (especialidadeBanco != null) {
            especialidadeBanco.setStatus("D");
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
