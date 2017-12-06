package cn.exercise.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.TimerTask;

/**
 * 定时调度任务类
 */
public class MyTimerTask extends TimerTask{
	
	private String name;
	
	public MyTimerTask(String name){
		this.name = name;
	}

	/**
	 * 在此方法中编写需要定时执行的业务逻辑代码
	 */
	@Override
	public void run() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("当前timer的名字是：" + name + "，当前时间为: " + sf.format(calendar.getTime()));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
