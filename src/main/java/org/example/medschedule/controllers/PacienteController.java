package org.example.medschedule.controllers;

import org.example.medschedule.dto.AtualizaStatusPacienteRequest;
import org.example.medschedule.dto.PacienteRequest;
import org.example.medschedule.dto.PacienteResponse;
import org.example.medschedule.entities.Paciente;
import org.example.medschedule.repositories.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller REST de Paciente: expõe as rotas HTTP do CRUD (POST, GET, PUT, PATCH, DELETE)
 * e traduz cada requisição em operações no banco via PacienteRepository.
 *
 * @RestController = classe que responde requisições HTTP devolvendo JSON.
 * @RequestMapping("/pacientes") = prefixo de todas as rotas desta classe.
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    // Acesso ao banco (camada de persistência).
    private final PacienteRepository pacienteRepository;

    // Injeção de dependência pelo construtor: o Spring cria e entrega o repository pronto.
    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /** GET /pacientes -> lista todos os pacientes salvos (200 OK). */
    @GetMapping
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll(); // SELECT * FROM paciente
    }

    /** GET /pacientes/{id} -> busca um paciente pelo id (200 OK ou 404 Not Found). */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultaPorId(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)                      // achou: 200 com o paciente
                .orElse(ResponseEntity.notFound().build());   // não achou: 404
    }

    /**
     * POST /pacientes -> cadastra um novo paciente a partir do JSON recebido (201 Created).
     * @RequestBody converte o JSON do corpo da requisição no objeto PacienteRequest (DTO).
     */
    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrarPaciente(@RequestBody PacienteRequest pacienteRequest) {
        // Copia os dados do DTO para a entidade que será gravada.
        Paciente pacienteBanco = new Paciente();
        pacienteBanco.setNome(pacienteRequest.getNome());
        pacienteBanco.setCpf(pacienteRequest.getCpf());
        pacienteBanco.setTelefone(pacienteRequest.getTelefone());
        pacienteBanco.setDataNascimento(pacienteRequest.getDataNascimento());
        pacienteBanco.setDataCadastro(LocalDateTime.now());
        pacienteBanco.setStatus("A"); // todo novo registro nasce ativo

        // INSERT no banco; o save devolve a entidade já com o id gerado.
        pacienteBanco = pacienteRepository.save(pacienteBanco);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PacienteResponse(pacienteBanco.getId(), "Cadastro com sucesso!"));
    }

    /** PUT /pacientes/{id} -> atualiza os dados de um paciente existente (200 OK ou 404). */
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest) {
        Paciente pacienteBanco = pacienteRepository.findById(id).orElse(null);

        if (pacienteBanco != null) {
            pacienteBanco.setNome(pacienteRequest.getNome());
            pacienteBanco.setCpf(pacienteRequest.getCpf());
            pacienteBanco.setTelefone(pacienteRequest.getTelefone());
            pacienteBanco.setDataNascimento(pacienteRequest.getDataNascimento());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());
            pacienteRepository.save(pacienteBanco); // como o id já existe, o save faz UPDATE

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Paciente atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build(); // id inexistente: 404
    }

    /** PATCH /pacientes/{id}/status -> altera somente o status do paciente (atualização parcial). */
    @PatchMapping("/{id}/status")
    public ResponseEntity<PacienteResponse> atualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusPacienteRequest pacienteRequest) {
        Paciente pacienteBanco = pacienteRepository.findById(id).orElse(null);

        if (pacienteBanco != null) {
            pacienteBanco.setStatus(pacienteRequest.getStatus());
            pacienteBanco.setDataAtualizacao(LocalDateTime.now());
            pacienteRepository.save(pacienteBanco);

            return ResponseEntity.ok(new PacienteResponse(pacienteBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE /pacientes/{id} -> remove o registro do banco de dados (DELETE FROM paciente).
     * Retorna 200 OK se removeu ou 404 Not Found se o id não existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> deletarPaciente(@PathVariable Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
