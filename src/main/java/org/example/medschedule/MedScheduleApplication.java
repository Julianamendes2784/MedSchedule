package org.example.medschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal (ponto de entrada) da aplicação MedSchedule.
 *
 * @SpringBootApplication junta três coisas: configuração automática do Spring Boot,
 * varredura de componentes (encontra controllers, repositories, configs neste pacote
 * e nos subpacotes) e permite declarar beans de configuração.
 */
@SpringBootApplication
public class MedScheduleApplication {

    /**
     * Método executado ao iniciar o programa: sobe o servidor web embutido (Tomcat, porta 8080),
     * conecta ao banco de dados e registra as rotas da API.
     */
    public static void main(String[] args) {

    System.out.println("MedSchedule application started.");
        SpringApplication.run(MedScheduleApplication.class, args);
    }





}
