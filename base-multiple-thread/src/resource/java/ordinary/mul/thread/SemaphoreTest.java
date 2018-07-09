/**
 * 
 */
package resource.java.ordinary.mul.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore �ź���Demo
 * 
 * Semaphore����ά����ǰ����������̸߳����������ṩ��ͬ�����ơ�ʹ��Semaphore���Կ���
 * ͬʱ������Դ���̸߳��������磬ʵ��һ���ļ�����Ĳ�����������������ֻ����3������ͬʱ��ȡ�ļ�����������10���߳���Ҫ���ʣ�
 * ֻ�е���3���߳����κ�һ��������Ϻ�����7���߳����е�����һ������ȥ���ʡ�
 * 
 * @author xiao
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// �����ź���������Ϊ3
		final Semaphore sp = new Semaphore(3);
		// ����10���߳�
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// ����ź���
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�" + Thread.currentThread().getName() + "��ǰ����" + (3 - sp.availablePermits() + "������"));
					try {
						// ģ���̲߳���
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�" + Thread.currentThread().getName() + "�����뿪");
					// �ͷ��ź�
					sp.release();
					System.out.println("�߳�" + Thread.currentThread().getName() + "���뿪����ǰ����" + (3 - sp.availablePermits() + "������"));
				}
			};
			// ִ���߳�
			service.execute(runnable);
		}
	}
}
