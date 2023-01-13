/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evoluti.quartz.teste;

import com.evoluti.quartz.teste.jobs.TesteJob;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
public class Tasks {

    private Scheduler scheduler;

    public Tasks() throws SchedulerException {
        if (scheduler == null) {
            scheduler = new StdSchedulerFactory().getScheduler();
        }
    }

    public void adicionarTask(String jobKey, String triggerKey, String cron) throws SchedulerException {
        adicionarTask(jobKey, triggerKey, cron, TesteJob.class);
    }
    
    public void adicionarTask(String jobKey, String triggerKey, String cron, Class<? extends Job> jobClass) throws SchedulerException {
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerKey, "group1")
                .withSchedule(cronSchedule(cron))
                .build();

        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(JobKey.jobKey(jobKey, "group1")).build();

        adicionarTask(jobDetail, trigger);
    }
    
    public void adicionarTask(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void startTasks() throws SchedulerException {
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
    }

    public void stopTasks() throws SchedulerException {
        if (!scheduler.isShutdown() && scheduler.isStarted()) {
            scheduler.pauseAll();
        }
    }

    public void resumeTasks() throws SchedulerException {
        if (scheduler != null) {
            scheduler.resumeAll();
        }
    }

    public void shutdownTasks() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}
