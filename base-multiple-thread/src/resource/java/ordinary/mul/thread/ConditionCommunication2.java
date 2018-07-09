package resource.java.ordinary.mul.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������Ŀ���߳�1ѭ��10�Σ������߳�2ѭ��20�Σ������߳�3ѭ��30�Σ��ֻص��߳�1ѭ��10�Σ������ֻص��߳�2ѭ��20�Ρ��������ѭ��50�Ρ�д������
 * 
 * ���飺Ҫ�õ���ͬ���ݣ�����ͬ��������ͬ�㷨�����ɸ�����Ӧ�ù���ͬһ�������ϣ�������������˸���ۺͳ���Ľ�׳�ԡ�
 * 
 * ����conditionͨ�ŷ�ʽ��Condition�Ĺ��������ڴ�ͳ�̼߳����е�Object.wait()��Object.notify�Ĺ��ܡ�
 * �ڵȴ�Conditionʱ������������ٻ��ѡ��� ��ͨ����Ϊ�Ի���ƽ̨������ò���Condition��ʵ�ֶ�·�ȴ��������
 */
public class ConditionCommunication2 {
	public static void main(String[] args) {

		final Business business = new Business();

		// �߳�2ѭ��sub2()
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub2(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// �߳�3ѭ��sub3()
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub3(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// �����̣߳��߳�1ѭ��sub1()
		for (int i = 1; i <= 50; i++) {
			try {
				business.sub1(i);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * ҵ�����ͣ�������ɫ��ͬ������
	 */
	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		// sub()�����Ƿ�����б�ʶ
		private int shouldSub = 1;

		/**
		 * ѭ��10�δ�ӡ�ķ���sub1()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub1(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 1) {  // �� shouldSub ��Ϊ 1 ʱ����ȴ�
					condition1.await(); // ����д��condition.wait();�������wait()��Object�ķ���
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub1 thread :   ��" + i + "�У� ��" + j + "��");
				}
				shouldSub = 2; // ִ��forѭ���󣬱�־��һ������ѭ���ķ���ʱsub2()
				condition2.signal(); // condition2���ź�
			} finally {
				lock.unlock();
			}
		}

		/**
		 * ѭ��20�δ�ӡ�ķ���sub2()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub2(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 2) {  // �� shouldSub ��Ϊ 2 ʱ����ȴ�
					condition2.await(); // ����д��condition.wait();�������wait()��Object�ķ���
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub2 thread :   ��" + i + "�У� ��" + j + "��");
				}
				shouldSub = 3; // ִ��forѭ���󣬱�־��һ������ѭ���ķ���ʱsub3()
				condition3.signal(); // condition3���ź�
			} finally {
				lock.unlock();
			}
		}

		/**
		 * ѭ��30�δ�ӡ�ķ���sub3()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub3(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 3) {  // �� shouldSub ��Ϊ 3ʱ����ȴ�
					condition3.await(); // ����д��condition.wait();�������wait()��Object�ķ���
				}
				for (int j = 1; j <= 30; j++) {
					System.out.println("sub3 thread :   ��" + i + "�У� ��" + j + "��");
				}
				shouldSub = 1; // ִ��forѭ���󣬱�־��һ������ѭ���ķ���ʱsub1()
				condition1.signal(); // condition1���ź�
			} finally {
				lock.unlock();
			}
		}

	}
}
