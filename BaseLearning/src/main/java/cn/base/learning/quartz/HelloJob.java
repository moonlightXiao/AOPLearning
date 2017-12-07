package cn.base.learning.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob implements Job{

	/**
	 * 业务逻辑
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("Hi ~~, now time is：" + sdf.format(calendar.getTime()));
		// 获取JobDetail信息
		JobKey key = context.getJobDetail().getKey();
		System.out.println("JobDetail的名字和组是： " + key.getName() + "，" + key.getGroup());
		// 获取Trigger信息
		TriggerKey trkey = context.getTrigger().getKey();
		System.out.println("Trigger的名字和组是： " + trkey.getName() + "，" + trkey.getGroup());
		// 获取JobDetai和Trigger的JobDataMap对象
		JobDataMap jobDetailDataMap = context.getJobDetail().getJobDataMap();
		JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
		System.out.println(jobDetailDataMap.getString("message"));
		System.out.println(triggerDataMap.getString("message"));
	}

}
