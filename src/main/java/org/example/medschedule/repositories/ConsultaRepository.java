package org.example.medschedule.repositories;

import org.example.medschedule.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository de Consulta: CRUD gerado automaticamente pelo Spring Data JPA.
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // "Query method": o Spring gera o SQL a partir do nome do método
    // (busca as consultas em que medico.id = medicoId).
    List<Consulta> findByMedicoId(Long medicoId);
}
