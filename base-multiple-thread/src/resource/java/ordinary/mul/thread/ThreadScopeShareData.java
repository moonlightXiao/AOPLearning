package resource.java.ordinary.mul.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程数据共享demo（线程内共享变量，线程外独立）
 * 
 * 方法一：定义Map<Thread, Integer>对象，存储共享数据
 */
public class ThreadScopeShareData {

	private static int data = 0;
	// 存储每个线程中独立的数据
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					data = new Random().nextInt();
					threadData.put(Thread.currentThread(), data); // 将当前线程的线程和线程共享数据存入map中
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					new A().get(); // 调用模块一的方法
					new B().get(); // 调用模块二的方法
				}
			}).start();
		}
	}

	// 模块一
	static class A {
		public void get() {
			int data = threadData.get(Thread.currentThread()); // 从map中获取当前线程数据对象
			System.out.println("A from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}
	// 模块二
	static class B {
		public void get() {
			int data = threadData.get(Thread.currentThread()); // 从map中获取当前线程数据对象
			System.out.println("B from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}
}
