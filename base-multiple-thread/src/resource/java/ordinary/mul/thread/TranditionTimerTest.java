package resource.java.ordinary.mul.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TranditionTimerTest {
	
	public static void main(String[] args) throws InterruptedException {

		/*
		 * 一种连续调用定时器方式：定义两个定时器，一个用于触发定时器，一个用于循环调用触发循环
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
		 * 定时器
		 * 第一个参数是TimerTask()对象，第二是第一个执行的时间，第三个是第一次执行完后每隔多少秒开始执行
		 */
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Ok, you show now ~~");
			}
		}, 3000, 5000);
		
	}
}
