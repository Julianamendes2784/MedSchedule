package org.example.medschedule.controllers;

import org.example.medschedule.dto.AtualizaStatusConsultaRequest;
import org.example.medschedule.dto.ConsultaRequest;
import org.example.medschedule.dto.ConsultaResponse;
import org.example.medschedule.entities.Consulta;
import org.example.medschedule.entities.StatusConsulta;
import org.example.medschedule.repositories.ConsultaRepository;
import org.example.medschedule.repositories.EspecialidadeRepository;
import org.example.medschedule.repositories.PacienteRepository;
import org.example.medschedule.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller REST de Consulta: expõe o CRUD de agendamentos em /consultas.
 * A consulta referencia paciente, médico e especialidade apenas pelo id (sem relacionamento entre
 * tabelas); por isso este controller também usa os repositories dessas entidades para verificar
 * se cada id informado existe antes de agendar.
 */
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspecialidadeRepository especialidadeRepository;

    // Injeção de dependência pelo construtor: o Spring entrega os quatro repositories prontos.
    public ConsultaController(ConsultaRepository consultaRepository,
                              PacienteRepository pacienteRepository,
                              UsuarioRepository usuarioRepository,
                              EspecialidadeRepository especialidadeRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    /** GET /consultas -> lista todas as consultas (200 OK). */
    @GetMapping
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    /** GET /consultas/{id} -> busca uma consulta pelo id (200 OK ou 404 Not Found). */
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> consultaPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** GET /consultas/medico/{medicoId} -> lista as consultas de um médico específico. */
    @GetMapping("/medico/{medicoId}")
    public List<Consulta> consultaConsultasPorMedico(@PathVariable Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    /**
     * POST /consultas -> agenda uma nova consulta (201 Created).
     * O JSON traz os ids de paciente, médico e especialidade; se algum não existir, retorna 404.
     */
    @PostMapping
    public ResponseEntity<ConsultaResponse> cadastrarConsulta(@RequestBody ConsultaRequest consultaRequest) {
        // Os três ids são obrigatórios: sem eles não há como agendar (400 Bad Request).
        if (consultaRequest.getPacienteId() == null
                || consultaRequest.getMedicoId() == null
                || consultaRequest.getEspecialidadeId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Só agenda se paciente, médico e especialidade existirem (senão 404).
        // A consulta guarda apenas os ids, sem relacionamento (chave estrangeira) entre as tabelas.
        if (!pacienteRepository.existsById(consultaRequest.getPacienteId())
                || !usuarioRepository.existsById(consultaRequest.getMedicoId())
                || !especialidadeRepository.existsById(consultaRequest.getEspecialidadeId())) {
            return ResponseEntity.notFound().build();
        }

        Consulta consultaBanco = new Consulta();
        consultaBanco.setPacienteId(consultaRequest.getPacienteId());
        consultaBanco.setMedicoId(consultaRequest.getMedicoId());
        consultaBanco.setEspecialidadeId(consultaRequest.getEspecialidadeId());
        consultaBanco.setDataHora(consultaRequest.getDataHora());
        consultaBanco.setStatus(StatusConsulta.AGENDADA); // toda consulta nova começa como AGENDADA
        consultaBanco.setDataCadastro(LocalDateTime.now());

        consultaBanco = consultaRepository.save(consultaBanco);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ConsultaResponse(consultaBanco.getId(), "Cadastro com sucesso!"));
    }

    /** PUT /consultas/{id} -> reagenda a consulta, alterando a data/hora (200 OK ou 404). */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaRequest consultaRequest) {
        Consulta consultaBanco = consultaRepository.findById(id).orElse(null);

        if (consultaBanco != null) {
            consultaBanco.setDataHora(consultaRequest.getDataHora());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());
            consultaRepository.save(consultaBanco);

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Consulta atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /** PATCH /consultas/{id}/status -> altera somente o status (AGENDADA, REALIZADA ou CANCELADA). */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ConsultaResponse> atualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusConsultaRequest consultaRequest) {
        Consulta consultaBanco = consultaRepository.findById(id).orElse(null);

        if (consultaBanco != null) {
            consultaBanco.setStatus(consultaRequest.getStatus());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());
            consultaRepository.save(consultaBanco);

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE /consultas/{id} -> remove o registro do banco de dados (200 OK ou 404 Not Found).
     * Para apenas cancelar mantendo o histórico, use PATCH /consultas/{id}/status com "CANCELADA".
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponse> deletarConsulta(@PathVariable Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
