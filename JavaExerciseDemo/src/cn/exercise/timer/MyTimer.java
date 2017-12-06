package cn.exercise.timer;

import java.util.Calendar;
import java.util.Timer;

/**
 * ��ʱ������
 * schedule()�����������÷�
 * scheduleAtFixedRate�������÷�
 */
public class MyTimer {

	public static void main(String[] args) {
		// 1������һ��timerʵ��
		Timer timer = new Timer();
		// 2������һ��M��TimerTask
		MyTimerTask myTimerTask = new MyTimerTask("task NO.1");
		
		// 3��ͨ��timer��ʱ��Ƶ����myTimerTaskҵ���߼�
		/************* 3.1 schedule�������÷� *****************/
		// 3.1.1 ��һ���÷�:�ȴ�delay�����ִ���ҽ�ִ��һ��task
//		timer.schedule(myTimerTask, 2000L);

		// 3.1.2 �ڶ����÷�:�ȴ�delay������״�ִ��task��֮��ÿ��period�����ظ�ִ��һ��task��
//		myTimerTask.setName("task NO.2");
//		timer.schedule(myTimerTask, 2000L, 1000L); // �ڶ���������ʾ�ӳٿ�ʼִ�е�ʱ�䣬������������ʾ���೤ʱ��ִ��һ�Σ����������ʾ2���ʼִ�У�ÿ��1��ִ��һ�Σ�
	
		// 3.1.3 �������÷�:ʱ����ڻ򳬹�timeʱִ���ҽ�ִ��һ��task��
		Calendar calendar = Calendar.getInstance();
//		myTimerTask.setName("task NO.3");
//		timer.schedule(myTimerTask, calendar.getTime()); 
		
		// 3.1.4 �������÷�:ʱ����ڻ򳬹�timeʱ�״�ִ��task��֮��ÿ��period�����ظ�ִ��һ��task
//		myTimerTask.setName("task NO.4");
//		timer.schedule(myTimerTask, calendar.getTime(), 2000L); 

		/************* 3.2 scheduleAtFixedRate�������÷� *****************/
		// 3.2.1 ��һ���÷�:�ȴ�delay������״�ִ��task��֮��ÿ��period�����ظ�ִ��һ��task
		myTimerTask.setName("task-scheduleAtFixedRate NO.1");
		timer.scheduleAtFixedRate(myTimerTask, calendar.getTime(), 2000L);  
		
		// 3.2.4 �ڶ����÷�:ʱ����ڻ򳬹�timeʱ�״�ִ��task��֮��ÿ��period�����ظ�ִ��һ��task
		myTimerTask.setName("task-scheduleAtFixedRate NO.2");
		timer.scheduleAtFixedRate(myTimerTask, 1000L, 2000L); 

	}
}
