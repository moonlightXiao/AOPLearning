package resource.java.ordinary.mul.thread;

/**
 * 多线程访问共享对象和数据的方式
 * 方式二：共享的代码不一样
 * 
 * @author admin
 *
 */
public class MultiThreadShareData3 {
	public static void main(String[] args) {

		// 第二种方式：定义多个Runnable的实现类，然后再不同的Runnable实现类中执行公用对象的不同代码
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
