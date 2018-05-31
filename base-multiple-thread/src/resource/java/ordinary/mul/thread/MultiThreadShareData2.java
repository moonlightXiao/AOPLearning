package resource.java.ordinary.mul.thread;

/**
 * 多线程访问共享对象和数据的方式 方式二：共享的代码不一样
 * 
 * @author xiao
 *
 */
public class MultiThreadShareData2 {
	public static void main(String[] args) {

		// 第一种方式：在各个线程的中共用一个对象，在各自的run()方法中执行共用对象的不同代码
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
