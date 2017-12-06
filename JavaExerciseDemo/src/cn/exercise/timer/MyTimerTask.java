package cn.exercise.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.TimerTask;

/**
 * ��ʱ����������
 */
public class MyTimerTask extends TimerTask{
	
	private String name;
	
	public MyTimerTask(String name){
		this.name = name;
	}

	/**
	 * �ڴ˷����б�д��Ҫ��ʱִ�е�ҵ���߼�����
	 */
	@Override
	public void run() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("��ǰtimer�������ǣ�" + name + "����ǰʱ��Ϊ: " + sf.format(calendar.getTime()));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
