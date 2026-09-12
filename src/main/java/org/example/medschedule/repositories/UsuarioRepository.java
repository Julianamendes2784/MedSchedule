package org.example.medschedule.repositories;

import org.example.medschedule.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsUsuarioByCpfAndSenha(String cpf, String senha);

    Optional<Usuario> getUsuarioByCpf(String cpf);

    Optional<List<Usuario>> getUsuariosByStatus(String status);
}
