package resource.java.ordinary.mul.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳ص�ʹ��ʵ��
 * 
 * @author xiao
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newFixedThreadPool(3); // �����̶���С���̳߳أ���3���̵߳��̳߳أ��̶��߳������̳߳أ�
//		ExecutorService threadPool = Executors.newCachedThreadPool(); // ���������̳߳أ��̳߳ص��߳���Ŀ��ʵ�������̵߳���Ŀ�仯���仯
		ExecutorService threadPool = Executors.newSingleThreadExecutor(); // ������һ�̳߳أ��̳߳���ֻ��һ���߳�
		for (int i = 0; i < 10; i++) {
			final int task = i; // ��Ҫ��task��������Ϊfinal���������̵߳�runnable�в��ܷ���
			threadPool.execute(new Runnable() { // ÿ��runnable��ʾһ������
				@Override
				public void run() {
					for (int i = 1; i <= 10; i++) { // ��ѭ���е�i������ѭ����i������ͬһ�������еı��������Բ����ͻ
						System.out.println(Thread.currentThread().getName() + " is loop of " + i + " task for " + task);
					}
				}
			});
		}
		System.out.println("10 tasks have committed! ");
		threadPool.shutdown();// �̳߳������߳�����ʱ����ر��̳߳�

		/**
		 * ���̳߳�������ʱ�������� ScheduledExecutorServiced��schedule������
		 * ���ص�ScheduledFuture�������ȡ������֧�ּ���ظ�����Ķ�ʱ��ʽ��
		 * ��ֱ��֧�־��Զ�ʱ��ʽ����Ҫת�������ʱ�䷽ʽ
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("�̳߳ض�ʱ��");
			}
		}, 6, 1, TimeUnit.SECONDS);
	}
}
