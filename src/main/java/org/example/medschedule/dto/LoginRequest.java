package org.example.medschedule.dto;

/**
 * DTO de ENTRADA do POST /login: o "login" é o CPF do usuário, acompanhado da senha.
 */
public class LoginRequest {

    // Construtor vazio: necessário para o Jackson converter o JSON recebido neste objeto.
    public LoginRequest() {
    }

    private String login;

    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
