package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusConsultaRequest;
import org.example.medschedule.DTO.ConsultaRequest;
import org.example.medschedule.DTO.ConsultaResponse;
import org.example.medschedule.entities.Consulta;
import org.example.medschedule.entities.Especialidade;
import org.example.medschedule.entities.Paciente;
import org.example.medschedule.entities.StatusConsulta;
import org.example.medschedule.entities.Usuario;
import org.example.medschedule.repositories.ConsultaRepository;
import org.example.medschedule.repositories.EspecialidadeRepository;
import org.example.medschedule.repositories.PacienteRepository;
import org.example.medschedule.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public ConsultaController(ConsultaRepository consultaRepository,
                              PacienteRepository pacienteRepository,
                              UsuarioRepository usuarioRepository,
                              EspecialidadeRepository especialidadeRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    @GetMapping
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> consultaPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medico/{medicoId}")
    public List<Consulta> consultaConsultasPorMedico(@PathVariable Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> CadastrarConsulta(@RequestBody ConsultaRequest consultaRequest) {
        Paciente paciente = pacienteRepository.findById(consultaRequest.getPacienteId()).orElse(null);
        Usuario medico = usuarioRepository.findById(consultaRequest.getMedicoId()).orElse(null);
        Especialidade especialidade = especialidadeRepository.findById(consultaRequest.getEspecialidadeId()).orElse(null);

        if (paciente == null || medico == null || especialidade == null) {
            return ResponseEntity.notFound().build();
        }

        Consulta consultaBanco = new Consulta();
        consultaBanco.setPaciente(paciente);
        consultaBanco.setMedico(medico);
        consultaBanco.setEspecialidade(especialidade);
        consultaBanco.setDataHora(consultaRequest.getDataHora());
        consultaBanco.setStatus(StatusConsulta.AGENDADA);
        consultaBanco.setDataCadastro(LocalDateTime.now());

        consultaBanco = consultaRepository.save(consultaBanco);

        return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> AtualizarConsulta(@PathVariable Long id, @RequestBody ConsultaRequest consultaRequest) {
        Consulta consultaBanco = consultaRepository.findById(id).orElse(null);

        if (consultaBanco != null) {
            consultaBanco.setDataHora(consultaRequest.getDataHora());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());
            consultaRepository.save(consultaBanco);

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Consulta atualizada com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ConsultaResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusConsultaRequest consultaRequest) {
        Consulta consultaBanco = consultaRepository.findById(id).orElse(null);

        if (consultaBanco != null) {
            consultaBanco.setStatus(consultaRequest.getStatus());
            consultaBanco.setDataAtualizacao(LocalDateTime.now());
            consultaRepository.save(consultaBanco);

            return ResponseEntity.ok(new ConsultaResponse(consultaBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponse> DeletarConsulta(@PathVariable Long id) {
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
