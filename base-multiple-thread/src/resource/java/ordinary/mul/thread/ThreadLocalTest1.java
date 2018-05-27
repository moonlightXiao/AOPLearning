package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * 线程数据共享demo（ThreadLocal：线程内共享变量，线程外独立）
 * 
 * 场景一：线程内只需共享一个变量
 * 方法二：使用ThreadLocal
 */
public class ThreadLocalTest1 {

	// 创建一个ThreadLocal对象
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt(); // 生成当前线程的数据
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					x.set(data); // 将当前线程的数据放入ThreadLocal
					new A().get(); // 调用模块一的方法
					new B().get(); // 调用模块二的方法
				}
			}).start();
		}
	}

	// 模块一
	static class A {
		public void get() {
			int data = x.get();// 获取当前线程的数据
			System.out.println("A from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}

	// 模块二
	static class B {
		public void get() {
			int data = x.get();// 获取当前线程的数据
			System.out.println("B from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}
}
