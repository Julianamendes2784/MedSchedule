package org.example.medschedule.dto;

/**
 * DTO de SAÍDA do POST /login: devolve a mensagem de boas-vindas quando o login é aceito.
 */
public class LoginResponse {

    // Construtor vazio: necessário para o Jackson serializar o objeto.
    public LoginResponse() {
    }

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
