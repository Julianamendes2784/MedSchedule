package org.example.medschedule.controllers;

import org.example.medschedule.DTO.AtualizaStatusUsuarioRequest;
import org.example.medschedule.DTO.UsuarioRequest;
import org.example.medschedule.DTO.UsuarioResponse;
import org.example.medschedule.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public String ConsultaUsuario() {
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public Usuario ConsultaUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = new Usuario();

        usuario.setCpf("12345678900");
        usuario.setNome("João da Silva");
        usuario.setDataNascimento("01/01/1990");

        return usuario;
    }

    @GetMapping("/empresa/{empresaId}")
    public Usuario ConsultaUsuarioPorEmpresa(@PathVariable Long empresaId) {
        Usuario usuarioConstructorCompleto = new Usuario("João da Silva", "12345678900", "01/01/1990");
        return usuarioConstructorCompleto;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> CadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioBanco = new Usuario();
        usuarioBanco.setNome(usuarioRequest.getNome());
        usuarioBanco.setCpf(usuarioRequest.getCpf());
        usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());

        usuarioBanco.setDataCadastro(LocalDateTime.now());
        usuarioBanco.setStatus("A");

        return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(), "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> AtualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
        //Consulta no banco
        Usuario usuarioBanco = new Usuario();

        if (usuarioBanco != null) {
            usuarioBanco.setNome(usuarioRequest.getNome());
            usuarioBanco.setCpf(usuarioRequest.getCpf());
            usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(), "Usuario atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UsuarioResponse> AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusUsuarioRequest usuarioRequest) {
        //Consulta no banco
        Usuario usuarioBanco = new Usuario();

        if (usuarioBanco != null) {
            usuarioBanco.setStatus(usuarioRequest.getStatus());
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(), "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> DeletarUsuario(@PathVariable Long id) {
        //Consulta no banco
        Usuario usuarioBanco = new Usuario();

        if (usuarioBanco != null) {
            usuarioBanco.setStatus("D");
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
