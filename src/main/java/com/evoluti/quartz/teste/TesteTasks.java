/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evoluti.quartz.teste;

import java.util.Arrays;
import java.util.List;
import org.quartz.SchedulerException;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
public class TesteTasks {

    public static void main(String[] args) throws SchedulerException {
        Tasks tasks = new Tasks();
        tasks.startTasks();

        List<String> horarios = Arrays.asList("17:14", "17:15", "17:17");

        for (String horario : horarios) {
            String horas = horario.split(":")[0];
            String minutos = horario.split(":")[1];

            String triggerKey = "TRIGGER_" + horas + minutos;
            String jobkey = "JOB_" + horas + minutos;
            
            StringBuilder cronString = new StringBuilder();
            cronString.append("0").append(" "); // segundos
            cronString.append(minutos).append(" "); // minutos
            cronString.append(horas).append(" "); // horas
            cronString.append("*").append(" "); // dia do mês
            cronString.append("*").append(" "); // mês
            cronString.append("?"); // dia da semana
            
            tasks.adicionarTask(jobkey, triggerKey, cronString.toString());
        }
        
    }
}
