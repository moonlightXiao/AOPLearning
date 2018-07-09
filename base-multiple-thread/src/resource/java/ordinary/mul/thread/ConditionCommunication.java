package resource.java.ordinary.mul.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������Ŀ�����߳�ѭ��10�Σ��������߳�ѭ��100�Σ������ֻص����߳�ѭ��10�Σ������ֻص����߳���ѭ��100�Σ����ѭ��50�Ρ�д������
 * 
 * ���飺Ҫ�õ���ͬ���ݣ�����ͬ��������ͬ�㷨�����ɸ�����Ӧ�ù���ͬһ�������ϣ�������������˸���ۺͳ���Ľ�׳�ԡ�
 * 
 * ����conditionͨ�ŷ�ʽ��Condition�Ĺ��������ڴ�ͳ�̼߳����е�Object.wait()��Object.notify�Ĺ��ܡ�
 * �ڵȴ�Conditionʱ������������ٻ��ѡ��� ��ͨ����Ϊ�Ի���ƽ̨������ò���Condition�����·�ȴ��������
 */
public class ConditionCommunication {
	public static void main(String[] args) {

		final Business business = new Business();

		// ���߳�ѭ��
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// ���߳�ѭ��
		for (int i = 1; i <= 50; i++) {
			try {
				business.mian(i);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * ҵ�����ͣ�������ɫ��ͬ������
	 */
	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		// sub()�����Ƿ�����б�ʶ
		private boolean bShouldSub = true;

		/**
		 * ѭ��100�δ�ӡ�ķ���sub()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub(int i) throws InterruptedException {
			lock.lock();
			try {
				while (!bShouldSub) { // �� bShouldSub Ϊ false ʱ����ȴ�
					condition.await(); // ����д��condition.wait();�������wait()��Object�ķ���
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread :   ��" + i + "�У� ��" + j + "��");
				}
				bShouldSub = false; // ִ��forѭ���󣬱�־sub()����������ִ��
				condition.signal(); // ���ź�
			} finally {
				lock.unlock();
			}
		}

		/**
		 * ѭ��100�δ�ӡ�ķ���mian()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void mian(int i) throws InterruptedException {
			lock.lock();
			try {
				while (bShouldSub) {
					condition.await(); // ����д��condition.wait();�������wait()��Object�ķ���
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread :   ��" + i + "�У� ��" + j + "��");
				}
				bShouldSub = true; // ִ��forѭ���󣬱�־sub()��������ִ����
				condition.signal(); // ���ź�
			} finally {
				lock.unlock();
			}
		}
	}
}
