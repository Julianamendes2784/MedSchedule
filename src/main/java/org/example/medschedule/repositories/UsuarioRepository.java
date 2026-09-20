package org.example.medschedule.repositories;

import org.example.medschedule.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository de Usuario: CRUD gerado automaticamente pelo Spring Data JPA,
 * mais consultas derivadas do nome dos métodos (query methods).
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Retorna true se existir usuário com esse CPF e senha (usado no login).
    boolean existsUsuarioByCpfAndSenha(String cpf, String senha);

    // Busca um usuário pelo CPF.
    Optional<Usuario> getUsuarioByCpf(String cpf);

    // Lista os usuários com determinado status (ex.: "A" ativos).
    Optional<List<Usuario>> getUsuariosByStatus(String status);
}
