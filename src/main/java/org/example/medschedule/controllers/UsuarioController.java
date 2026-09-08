package org.example.medschedule.controllers;

import org.apache.coyote.Response;
import org.example.medschedule.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @GetMapping
    public String ConsultaUsuario (){
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public Usuario ConsultaUsuarioPorId(@PathVariable Long id){
       Usuario usuario = new Usuario();

       usuario.setCpf("12345678900");
       usuario.setNome("João da Silva");
       usuario.setDataNascimento("01/01/1990");

        return usuario;

    }

    @GetMapping("/empresa/{empresaId}")
    public Usuario ConsultaUsuarioPorEmpresa(@PathVariable Long empresaId){
        Usuario usuarioConstructorCompleto = new Usuario("João da Silva", "12345678900", "01/01/1990");
        return usuarioConstructorCompleto;
    }

    @PostMapping
    public ResponseEntity<Usuario> CadastrarUsuario(@RequestBody Usuario usuarioRequest) {
        return ResponseEntity.ok(usuarioRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> AtualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {
        usuarioRequest.setId(id);
        return ResponseEntity.ok(usuarioRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> AtualizarParcialUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {
        usuarioRequest.setId(id);
        return ResponseEntity.ok(usuarioRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarUsuario(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
