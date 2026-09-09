package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusEspecialidadeRequest;
import org.example.medschedule.DTO.EspecialidadeRequest;
import org.example.medschedule.DTO.EspecialidadeResponse;
import org.example.medschedule.entities.Especialidade;
import org.example.medschedule.repositories.EspecialidadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeController(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @GetMapping
    public List<Especialidade> getEspecialidades() {
        return especialidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> consultaPorId(@PathVariable Long id) {
        return especialidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EspecialidadeResponse> CadastrarEspecialidade(@RequestBody EspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = new Especialidade();
        especialidadeBanco.setNome(especialidadeRequest.getNome());
        especialidadeBanco.setDataCadastro(LocalDateTime.now());
        especialidadeBanco.setStatus("A");

        especialidadeBanco = especialidadeRepository.save(especialidadeBanco);

        return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> AtualizarEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = especialidadeRepository.findById(id).orElse(null);

        if (especialidadeBanco != null) {
            especialidadeBanco.setNome(especialidadeRequest.getNome());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());
            especialidadeRepository.save(especialidadeBanco);

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Especialidade atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EspecialidadeResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusEspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = especialidadeRepository.findById(id).orElse(null);

        if (especialidadeBanco != null) {
            especialidadeBanco.setStatus(especialidadeRequest.getStatus());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());
            especialidadeRepository.save(especialidadeBanco);

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> DeletarEspecialidade(@PathVariable Long id) {
        Especialidade especialidadeBanco = especialidadeRepository.findById(id).orElse(null);

        if (especialidadeBanco != null) {
            especialidadeBanco.setStatus("D");
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());
            especialidadeRepository.save(especialidadeBanco);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
