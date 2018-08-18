package resource.java.ordinary.mul.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch demo ��ģ����з��������3���˶�Ա�Բ�ͬ���ٶȱ��ܡ���3���˶�Ա������󣬲��еõ��������
 * 
 * @author Smile
 *
 *         CountDownLatch�������Ϊһ���������ڳ�ʼ��ʱ���ó�ʼֵ����һ���߳���Ҫ�ȴ�ĳЩ���������ʱ����Ҫ����await()����������������߳̽�������״ֱ̬���ȴ��������̶߳�ִ����ɡ�ÿ����һ��countDown()�����ڲ���������1,�������ﵽ0ʱ�������еĵȴ��ſ�ʼִ�С�
 *         ����ʵ��һ���̣߳�Ҳ�����Ƕ���̣߳��ȴ������߳������ѣ�Ҳ����ʵ��һ���߳�֪ͨ����̵߳�Ч����
 *         ���������Ʋ���һ�������£����е��˶�Ա���ܿ�ʼ���ܣ��������е��˶�Ա���������б��������
 * 
 *         ���ķ���������countDown()��await()
 *         (1)countDown():ʹCountDownLatchά�����ڲ���������1,ÿ�����ȴ����߳���ɵ�ʱ�����
 *         (2)await():�߳���ִ�е�CountDownLatch��ʱ��Ὣ���߳���������
 */
public class CountdownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		// ����һ�������������
		final CountDownLatch cdOrder = new CountDownLatch(1);
		// ������������Ӧ��������
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("�߳�" + Thread.currentThread().getName() + "����׼����������");
						// �ȴ������������Ϊ0
						cdOrder.await();

						System.out.println("�߳�" + Thread.currentThread().getName() + "��������");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�����������Ӧ�������");
						// ����Ӧ����������1
						cdAnswer.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};
			service.execute(runnable);
		}

		// ���̷߳�����CountDownLatch cdOrder����
		Thread.sleep((long) (Math.random() * 10000));
		System.out.println("�߳�" + Thread.currentThread().getName() + "������������");
		// �������������1�������������Ϊ0����ʼ����cdOrder.await()���߳�
		cdOrder.countDown();
		System.out.println("�߳�" + Thread.currentThread().getName() + "�ѷ�������ȴ�����������");

		// �ȴ�����Ӧ��������Ϊ0ʱ���ټ���ִ��
		cdAnswer.await();
		System.out.println("�߳�" + Thread.currentThread().getName() + "���յ�ȫ����������");

		service.shutdown();
	}

}
