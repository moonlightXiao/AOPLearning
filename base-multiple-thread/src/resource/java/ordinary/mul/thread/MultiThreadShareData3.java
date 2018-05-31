package resource.java.ordinary.mul.thread;

/**
 * ���̷߳��ʹ����������ݵķ�ʽ
 * ��ʽ��������Ĵ��벻һ��
 * 
 * @author admin
 *
 */
public class MultiThreadShareData3 {
	public static void main(String[] args) {

		// �ڶ��ַ�ʽ��������Runnable��ʵ���࣬Ȼ���ٲ�ͬ��Runnableʵ������ִ�й��ö���Ĳ�ͬ����
		ShareData3 data3 = new ShareData3();
		new Thread(new MyRunnable1(data3)).start();
		new Thread(new MyRunnable2(data3)).start();

	}
}

class MyRunnable1 implements Runnable {

	private ShareData3 data3;

	public MyRunnable1(ShareData3 data3) {
		this.data3 = data3;
	}

	@Override
	public void run() {
		data3.decrement();
	}

}

class MyRunnable2 implements Runnable {

	private ShareData3 data3;

	public MyRunnable2(ShareData3 data3) {
		this.data3 = data3;
	}

	@Override
	public void run() {
		data3.increment();
	}

}

class ShareData3 {

	private int j = 0;

	public synchronized void increment() {
		setJ(getJ() + 1);
	}

	public synchronized void decrement() {
		setJ(getJ() - 1);
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

}
