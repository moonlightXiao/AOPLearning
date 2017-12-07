package cn.base.learning.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException {
		// 创建一个JobDetail实例，该实例与HelloJob Class绑定; 用withIdentity()方法表示JobDetail的唯一标识; 用usingJobData()方法传数据进JobDataMap中
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myjob").usingJobData("message", "jobdetail !!").build();
		// 创建一个Trigger实例，定义job立即执行，并且没两秒执行一次; 用withIdentity()方法表示Trigger的唯一标识; 用usingJobData()方法传数据进JobDataMap中
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger", "group1").usingJobData("message", "trigger !!").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
		// 创建一个Schedule 实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
