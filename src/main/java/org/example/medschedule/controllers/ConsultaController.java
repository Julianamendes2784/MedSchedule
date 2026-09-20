package org.example.medschedule.controllers;

import org.example.medschedule.dto.AtualizaStatusConsultaRequest;
import org.example.medschedule.dto.ConsultaRequest;
import org.example.medschedule.dto.ConsultaResponse;
import org.example.medschedule.entities.Consulta;
import org.example.medschedule.entities.Especialidade;
import org.example.medschedule.entities.Paciente;
import org.example.medschedule.entities.StatusConsulta;
import org.example.medschedule.entities.Usuario;
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
 * Como uma consulta se relaciona com paciente, médico e especialidade, este controller
 * também usa os repositories dessas entidades para localizar cada uma pelo id recebido.
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
        // Busca no banco cada entidade referenciada pelo id.
        Paciente paciente = pacienteRepository.findById(consultaRequest.getPacienteId()).orElse(null);
        Usuario medico = usuarioRepository.findById(consultaRequest.getMedicoId()).orElse(null);
        Especialidade especialidade = especialidadeRepository.findById(consultaRequest.getEspecialidadeId()).orElse(null);

        // Só agenda se as três existirem.
        if (paciente == null || medico == null || especialidade == null) {
            return ResponseEntity.notFound().build();
        }

        Consulta consultaBanco = new Consulta();
        consultaBanco.setPaciente(paciente);
        consultaBanco.setMedico(medico);
        consultaBanco.setEspecialidade(especialidade);
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

    /** DELETE /consultas/{id} -> exclusão LÓGICA: em vez de apagar, marca a consulta como CANCELADA. */
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponse> deletarConsulta(@PathVariable Long id) {
        Consulta consultaBanco = consultaRepository.findById(id).orElse(null);

        if (consultaBanco != null) {
            consultaBanco.setStatus(StatusConsulta.CANCELADA);
            consultaBanco.setDataAtualizacao(LocalDateTime.now());
            consultaRepository.save(consultaBanco);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
