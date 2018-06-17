package resource.java.ordinary.mul.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock ʵ��
 * Lock �� synchronized ��ʽ�����������
 * 
 * @author xiao
 *
 */
public class LockTest {
	
	public static void main(String[] args) {
		new LockTest().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					outputer.output("ABCDEFQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					outputer.output("abcdefaaaaaaaaaaaaaaaaaaaaa");
				}
			}
		}).start();
	}
	
}
class Outputer{
	Lock lock = new ReentrantLock(); // ������
	
	public void output(String s){
		lock.lock(); // ����
		int len = s.length();
		try {
			for (int i = 0; i < len; i++) {
				System.out.print(s.charAt(i));
			}
			System.out.println();
			
		} finally {
			lock.unlock(); // ����
		}
	}
}
