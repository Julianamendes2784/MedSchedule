package org.example.medschedule.controllers;

import org.example.medschedule.dto.AtualizaStatusUsuarioRequest;
import org.example.medschedule.dto.UsuarioRequest;
import org.example.medschedule.dto.UsuarioResponse;
import org.example.medschedule.entities.Usuario;
import org.example.medschedule.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller REST de Usuario (médicos/usuários do sistema): expõe o CRUD em /usuarios
 * e persiste os dados no banco via UsuarioRepository.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // Acesso ao banco (camada de persistência).
    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência pelo construtor: o Spring entrega o repository pronto.
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /** GET /usuarios -> lista todos os usuários (200 OK). */
    @GetMapping
    public List<Usuario> consultaUsuario() {
        return usuarioRepository.findAll();
    }

    /** GET /usuarios/{id} -> busca um usuário pelo id (200 OK ou 404 Not Found). */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultaUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST /usuarios -> cadastra um novo usuário a partir do JSON recebido (201 Created). */
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        // Copia os dados do DTO para a entidade que será gravada.
        Usuario usuarioBanco = new Usuario();
        usuarioBanco.setNome(usuarioRequest.getNome());
        usuarioBanco.setCpf(usuarioRequest.getCpf());
        usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());
        usuarioBanco.setSenha(usuarioRequest.getSenha());
        usuarioBanco.setDataCadastro(LocalDateTime.now());
        usuarioBanco.setStatus("A"); // todo novo registro nasce ativo

        // INSERT no banco; devolve a entidade com o id gerado.
        usuarioBanco = usuarioRepository.save(usuarioBanco);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UsuarioResponse(usuarioBanco.getId(), "Cadastro com sucesso!"));
    }

    /** PUT /usuarios/{id} -> atualiza os dados de um usuário existente (200 OK ou 404). */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setNome(usuarioRequest.getNome());
            usuarioBanco.setCpf(usuarioRequest.getCpf());
            usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());
            usuarioBanco.setSenha(usuarioRequest.getSenha());
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());
            usuarioRepository.save(usuarioBanco); // id já existe: o save faz UPDATE

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(), "Usuario atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /** PATCH /usuarios/{id}/status -> altera somente o status do usuário (atualização parcial). */
    @PatchMapping("/{id}/status")
    public ResponseEntity<UsuarioResponse> atualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusUsuarioRequest usuarioRequest) {
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setStatus(usuarioRequest.getStatus());
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());
            usuarioRepository.save(usuarioBanco);

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    /** DELETE /usuarios/{id} -> exclusão LÓGICA: marca o usuário com status "D" em vez de apagar a linha. */
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> deletarUsuario(@PathVariable Long id) {
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setStatus("D");
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());
            usuarioRepository.save(usuarioBanco);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
