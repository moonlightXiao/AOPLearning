package resource.java.ordinary.mul.thread;

/**
 * ������Ŀ�����߳�ѭ��10�Σ��������߳�ѭ��100�Σ������ֻص����߳�ѭ��10�Σ������ֻص����߳���ѭ��100�Σ����ѭ��50�Ρ�д������
 * 
 * ���飺Ҫ�õ���ͬ���ݣ�����ͬ��������ͬ�㷨�����ɸ�����Ӧ�ù���ͬһ�������ϣ�������������˸���ۺͳ���Ľ�׳�ԡ�
 *
 */
public class TranditionThreadCommunication {
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
}

/**
 * ҵ�����ͣ�������ɫ��ͬ������
 */
class Business {
	// sub()�����Ƿ�����б�ʶ
	private boolean bShouldSub = true;

	/**
	 * ѭ��100�δ�ӡ�ķ���sub()
	 * 
	 * @param i
	 * @throws InterruptedException
	 */
	public synchronized void sub(int i) throws InterruptedException {
		while (!bShouldSub) { // �� bShouldSub Ϊ false ʱ����ȴ�
			this.wait();
		}
		for (int j = 1; j <= 10; j++) {
			System.out.println("sub thread :   ��" + i + "�У� ��" + j + "��");
		}
		bShouldSub = false; // ִ��forѭ���󣬱�־sub()����������ִ��
		this.notify(); // �����߳�
	}

	/**
	 * ѭ��100�δ�ӡ�ķ���mian()
	 * 
	 * @param i
	 * @throws InterruptedException
	 */
	public synchronized void mian(int i) throws InterruptedException {
		while (bShouldSub) {
			this.wait();
		}
		for (int j = 1; j <= 100; j++) {
			System.out.println("main thread :   ��" + i + "�У� ��" + j + "��");
		}
		bShouldSub = true; // ִ��forѭ���󣬱�־sub()��������ִ����
		this.notify(); // �����߳�
	}
}
