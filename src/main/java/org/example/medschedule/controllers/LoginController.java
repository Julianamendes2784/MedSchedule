package org.example.medschedule.controllers;

import org.example.medschedule.dto.LoginRequest;
import org.example.medschedule.dto.LoginResponse;
import org.example.medschedule.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de login: verifica se existe um usuário cadastrado com o CPF (usado como login)
 * e a senha informados. É uma verificação simples da aula, sem token/sessão.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência pelo construtor.
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /** POST /login -> 200 OK com mensagem de boas-vindas se CPF e senha conferem; 401 Unauthorized se não. */
    @PostMapping
    public ResponseEntity<LoginResponse> logar(@RequestBody LoginRequest loginRequest) {

        // Consulta no banco se existe usuário com esse CPF e essa senha.
        if (usuarioRepository.existsUsuarioByCpfAndSenha(loginRequest.getLogin(), loginRequest.getSenha())) {

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMensagem("Bem vindo ao sistema MedSchedule!");

            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
