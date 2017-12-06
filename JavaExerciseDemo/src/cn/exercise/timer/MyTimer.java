package cn.exercise.timer;

import java.util.Calendar;
import java.util.Timer;

/**
 * 定时调度类
 * schedule()方法的四种用法
 * scheduleAtFixedRate和两种用法
 */
public class MyTimer {

	public static void main(String[] args) {
		// 1、创建一个timer实例
		Timer timer = new Timer();
		// 2、创建一个M有TimerTask
		MyTimerTask myTimerTask = new MyTimerTask("task NO.1");
		
		// 3、通过timer定时定频调用myTimerTask业务逻辑
		/************* 3.1 schedule的四种用法 *****************/
		// 3.1.1 第一种用法:等待delay毫秒后执行且仅执行一次task
//		timer.schedule(myTimerTask, 2000L);

		// 3.1.2 第二种用法:等待delay毫秒后首次执行task，之后每隔period毫秒重复执行一次task；
//		myTimerTask.setName("task NO.2");
//		timer.schedule(myTimerTask, 2000L, 1000L); // 第二个参数表示延迟开始执行的时间，第三个参数表示隔多长时间执行一次（所以这里表示2秒后开始执行，每隔1秒执行一次）
	
		// 3.1.3 第三种用法:时间等于或超过time时执行且仅执行一次task；
		Calendar calendar = Calendar.getInstance();
//		myTimerTask.setName("task NO.3");
//		timer.schedule(myTimerTask, calendar.getTime()); 
		
		// 3.1.4 第四种用法:时间等于或超过time时首次执行task，之后每隔period毫秒重复执行一次task
//		myTimerTask.setName("task NO.4");
//		timer.schedule(myTimerTask, calendar.getTime(), 2000L); 

		/************* 3.2 scheduleAtFixedRate的两种用法 *****************/
		// 3.2.1 第一种用法:等待delay毫秒后首次执行task，之后每隔period毫秒重复执行一次task
		myTimerTask.setName("task-scheduleAtFixedRate NO.1");
		timer.scheduleAtFixedRate(myTimerTask, calendar.getTime(), 2000L);  
		
		// 3.2.4 第二种用法:时间等于或超过time时首次执行task，之后每隔period毫秒重复执行一次task
		myTimerTask.setName("task-scheduleAtFixedRate NO.2");
		timer.scheduleAtFixedRate(myTimerTask, 1000L, 2000L); 

	}
}
