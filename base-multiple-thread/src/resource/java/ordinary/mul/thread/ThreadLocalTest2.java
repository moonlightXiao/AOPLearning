package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * 线程数据共享demo（ThreadLocal：线程内共享变量，线程外独立）
 * 
 * 场景二：线程内需共享多个变量
 * 方法一：定义一个共享数据结构的类，将共享数据结构类作为ThreadLocal的泛型
 */
public class ThreadLocalTest2 {

	// 创建一个ThreadLocal对象，并将MyThreadScopeData1座位泛值类型
	private static ThreadLocal<MyThreadScopeData1> myThreadScopeData = new ThreadLocal<MyThreadScopeData1>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					// 设置当前线程的数据对象，并且将对象放置在ThreadLocal对象中
					MyThreadScopeData1 myData = new MyThreadScopeData1();
					myData.setName(Thread.currentThread().getName());
					myData.setAge(data);
					myThreadScopeData.set(myData);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	// 模块一
	static class A {
		public void get() {
			MyThreadScopeData1 myData = myThreadScopeData.get(); // 获取当前线程数据对象
			System.out.println("A from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());

		}
	}

	// 模块二
	static class B {
		public void get() {
			MyThreadScopeData1 myData = myThreadScopeData.get(); // 获取当前线程数据对象
			System.out.println("B from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());
		}
	}
}

/**
 * 定义共享数据结构类
 */
class MyThreadScopeData1 {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
