package org.example.medschedule.controllers;

import org.example.medschedule.dto.AtualizaStatusEspecialidadeRequest;
import org.example.medschedule.dto.EspecialidadeRequest;
import org.example.medschedule.dto.EspecialidadeResponse;
import org.example.medschedule.entities.Especialidade;
import org.example.medschedule.repositories.EspecialidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller REST de Especialidade: expõe o CRUD em /especialidades
 * e persiste os dados no banco via EspecialidadeRepository.
 */
@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    // Acesso ao banco (camada de persistência).
    private final EspecialidadeRepository especialidadeRepository;

    // Injeção de dependência pelo construtor: o Spring entrega o repository pronto.
    public EspecialidadeController(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    /** GET /especialidades -> lista todas as especialidades (200 OK). */
    @GetMapping
    public List<Especialidade> getEspecialidades() {
        return especialidadeRepository.findAll();
    }

    /** GET /especialidades/{id} -> busca uma especialidade pelo id (200 OK ou 404 Not Found). */
    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> consultaPorId(@PathVariable Long id) {
        return especialidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST /especialidades -> cadastra uma nova especialidade a partir do JSON recebido (201 Created). */
    @PostMapping
    public ResponseEntity<EspecialidadeResponse> cadastrarEspecialidade(@RequestBody EspecialidadeRequest especialidadeRequest) {
        // Copia os dados do DTO para a entidade que será gravada.
        Especialidade especialidadeBanco = new Especialidade();
        especialidadeBanco.setNome(especialidadeRequest.getNome());
        especialidadeBanco.setDataCadastro(LocalDateTime.now());
        especialidadeBanco.setStatus("A"); // todo novo registro nasce ativo

        // INSERT no banco; devolve a entidade com o id gerado.
        especialidadeBanco = especialidadeRepository.save(especialidadeBanco);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EspecialidadeResponse(especialidadeBanco.getId(), "Cadastro com sucesso!"));
    }

    /** PUT /especialidades/{id} -> atualiza uma especialidade existente (200 OK ou 404). */
    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> atualizarEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = especialidadeRepository.findById(id).orElse(null);

        if (especialidadeBanco != null) {
            especialidadeBanco.setNome(especialidadeRequest.getNome());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());
            especialidadeRepository.save(especialidadeBanco); // id já existe: o save faz UPDATE

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Especialidade atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /** PATCH /especialidades/{id}/status -> altera somente o status (atualização parcial). */
    @PatchMapping("/{id}/status")
    public ResponseEntity<EspecialidadeResponse> atualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusEspecialidadeRequest especialidadeRequest) {
        Especialidade especialidadeBanco = especialidadeRepository.findById(id).orElse(null);

        if (especialidadeBanco != null) {
            especialidadeBanco.setStatus(especialidadeRequest.getStatus());
            especialidadeBanco.setDataAtualizacao(LocalDateTime.now());
            especialidadeRepository.save(especialidadeBanco);

            return ResponseEntity.ok(new EspecialidadeResponse(especialidadeBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /** DELETE /especialidades/{id} -> remove o registro do banco de dados (200 OK ou 404 Not Found). */
    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> deletarEspecialidade(@PathVariable Long id) {
        if (especialidadeRepository.existsById(id)) {
            especialidadeRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
