package org.example.medschedule.repositories;

import org.example.medschedule.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository de Especialidade: o Spring Data JPA gera a implementação do CRUD
 * (save, findAll, findById etc.) automaticamente a partir desta interface.
 */
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
