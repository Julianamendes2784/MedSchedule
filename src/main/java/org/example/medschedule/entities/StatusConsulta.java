package org.example.medschedule.entities;

/**
 * Situações possíveis de uma Consulta. Usar enum impede valores inválidos
 * (só esses três são aceitos, ao contrário de uma String livre).
 */
public enum StatusConsulta {
    AGENDADA,
    REALIZADA,
    CANCELADA
}
