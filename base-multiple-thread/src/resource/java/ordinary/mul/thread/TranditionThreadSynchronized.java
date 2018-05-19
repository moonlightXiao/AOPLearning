package resource.java.ordinary.mul.thread;
/**
 * �̻߳��⣺synchronized
 * @author admin
 *
 */
public class TranditionThreadSynchronized {
	public static void main(String[] args) {
		new TranditionThreadSynchronized().init();
	}

	public void init() {
		final Output ootput = new Output();
		
		// ��һ���߳�
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						ootput.output2("xiao");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// �ڶ����߳�
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						ootput.output2("hag");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	class Output {
		public void output(String name) {
			int len = name.length();
			// synchronized(XXX){}�У�XXX��ʾ����Ҫ��ס�Ķ���
			// ��thisָ���Ǻ�output2��ʵ�ֵ�Ч��һ����������Output����
			synchronized (this) {
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}

		public synchronized void output2(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
			// ��ʱ���ڲ�����Ҫ��ʶΪstatic class����ʾΪ�ⲿ�ࡣ
			// �����ʱ��Ҫ��output1()��������һ�£�����Ҫ��synchronized (this)�޸�Ϊsynchronized (Output.class)
	//		public static synchronized void output3(String name) {
	//			int len = name.length();
	//			for (int i = 0; i < len; i++) {
	//				System.out.print(name.charAt(i));
	//			}
	//			System.out.println();
	//		}
	}
}
