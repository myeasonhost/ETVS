//package com.eason.api.zb.task;
//
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class DynamicScheduledTask implements SchedulingConfigurer {
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    private static final String DEFAULT_CRON = "0/5 * * * * ?";
//    private String cron = DEFAULT_CRON;
//
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//                if (!cron.equals(DEFAULT_CRON)) {
//
//                }
//                // 定时任务的业务逻辑
//                System.out.println(cron+"动态修改定时任务cron参数，当前时间：" + dateFormat.format(new Date()));
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                // 定时任务触发，可修改定时任务的执行周期
//                CronTrigger trigger = new CronTrigger(cron);
//                Date nextExecDate = trigger.nextExecutionTime(triggerContext);
//                return nextExecDate;
//            }
//        });
//    }
//
//    public void setCron(String cron) {
//        this.cron = cron;
//    }
//}
