package org.example.medschedule.repositories;

import org.example.medschedule.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository de Paciente: camada de acesso ao banco. Ao estender JpaRepository<Paciente, Long>
 * (entidade, tipo do id), o Spring Data gera sozinho a implementação dos métodos prontos:
 * save (INSERT/UPDATE), findAll, findById, deleteById etc. — sem escrever SQL.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
