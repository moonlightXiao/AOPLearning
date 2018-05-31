package resource.java.ordinary.mul.thread;

/**
 * ���̷߳��ʹ����������ݵķ�ʽ ��ʽ��������Ĵ��벻һ��
 * 
 * @author xiao
 *
 */
public class MultiThreadShareData2 {
	public static void main(String[] args) {

		// ��һ�ַ�ʽ���ڸ����̵߳��й���һ�������ڸ��Ե�run()������ִ�й��ö���Ĳ�ͬ����
		final ShareData2 data1 = new ShareData2();
		new Thread(new Runnable() {

			@Override
			public void run() {
				data1.decrement();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				data1.increment();
			}
		}).start();

	}
}

class ShareData2 {

	private int j = 0;

	public synchronized void increment() {
		j++;
	}

	public synchronized void decrement() {
		j--;
	}

}
