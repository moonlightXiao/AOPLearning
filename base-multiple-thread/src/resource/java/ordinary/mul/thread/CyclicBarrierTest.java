package resource.java.ordinary.mul.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier
 * Demo��ģ���߳�1���߳�2���߳�3�Բ�ͬʱ�䵽�Ｏ�ϵ�1����һ���Բ�ͬ���ٶȵ��Ｏ�ϵ�2���������̶߳����Ｏ�ϵ�2����һ����������ϵ�3
 * 
 * @author Smile
 * 
 *         CyclicBarrier��һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ㡣
 *         ������������˾����������ĩʱ�䵽���彼��һ�����ȸ��ԴӼ����������˾���ϣ���ͬʱ��������԰�ֿ����棬�ڲ͹ݼ��Ϻ���ͬʱ��ʼ�۲͡�����ġ���˾���ϡ������͹ݼ��ϡ�����ָ���ǹ������ϵ㡣
 *         ���÷�����
 *         ��1��await()�������ڵ���await()������CyclicBarrier����������̲߳�������������״̬�ȴ������̵߳ĵ�����
 * 
 *         ��CountDownLatch��ͬ���Ǹ�barrier���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ����Cyclic�������ϣ�Barrier����
 */
public class CyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// �������ϣ���������Ҫ�ȴ�3���߳�
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�1�� ��ǰ����"
								+ (cb.getNumberWaiting() + 1) + "������"
								+ ((2 == cb.getNumberWaiting()) ? "���߳��Ѿ�ȫ�����Ｏ�ϵ�1,���������ߣ�" : ""));
						cb.await(); // ���õ�һ�����ϣ����3���߳�ȫ��ִ�е���ʱ���ż�������

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�2�� ��ǰ����"
								+ (cb.getNumberWaiting() + 1) + "������"
								+ ((2 == cb.getNumberWaiting()) ? "���߳��Ѿ�ȫ�����Ｏ�ϵ�2,���������ߣ�" : ""));
						cb.await(); // ���õڶ������ϣ����3���߳�ȫ��ִ�е���ʱ���ż�������

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�3�� ��ǰ����"
								+ (cb.getNumberWaiting() + 1) + "������"
								+ ((2 == cb.getNumberWaiting()) ? "���߳��Ѿ�ȫ�����Ｏ�ϵ�3,��ϣ�" : ""));
						cb.await(); // ���õ��������ϣ����3���߳�ȫ��ִ�е���ʱ���ż�������

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runable);
		}
		service.shutdown();
	}
}
