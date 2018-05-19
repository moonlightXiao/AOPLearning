package resource.java.ordinary.mul.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TranditionTimerTest {
	
	public static void main(String[] args) throws InterruptedException {

		/*
		 * һ���������ö�ʱ����ʽ������������ʱ����һ�����ڴ�����ʱ����һ������ѭ�����ô���ѭ��
		 */
		class myTimerTask extends TimerTask{

			@Override
			public void run() {
				System.out.println("Ok, you show now ~~");
				new Timer().schedule(new myTimerTask(), 2000);
			}
		}
		
		new Timer().schedule(new myTimerTask(), 2000);		
				
		while (true) {
			System.out.println(new Date().getSeconds());
			Thread.sleep(1000);
		}
		
	}
	
	private void ordinaryTimerTask() throws InterruptedException{
		/*
		 * ��ʱ��
		 * ��һ��������TimerTask()���󣬵ڶ��ǵ�һ��ִ�е�ʱ�䣬�������ǵ�һ��ִ�����ÿ�������뿪ʼִ��
		 */
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Ok, you show now ~~");
			}
		}, 3000, 5000);
		
	}
}
