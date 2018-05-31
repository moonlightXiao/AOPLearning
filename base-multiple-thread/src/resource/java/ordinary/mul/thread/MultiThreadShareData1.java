package resource.java.ordinary.mul.thread;

/**
 * 多线程访问共享对象和数据的方式 方式一：共享的代码是一样的
 * 
 * @author xiao
 *
 */
public class MultiThreadShareData1 {
	public static void main(String[] args) {
		// 对象共享
		ShareData1 data1 = new ShareData1();

		new Thread(data1).start();
		new Thread(data1).start();

	}
}

class ShareData1 implements Runnable {

	private int count = 100;

	@Override
	public void run() {
		while (count > 0) {
			count--;
		}
	}

}
