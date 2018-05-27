package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * 线程数据共享demo（ThreadLocal：线程内共享变量，线程外独立）
 * 
 * 场景二：线程内需共享多个变量 
 * 方法二：定义一个共享数据结构的类，ThreadLocal对象放入共享数据结构的类中，在共享数据结构类中存储所有线程的共享数据
 */
public class ThreadLocalTest3 {
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					// 设置当前线程的数据对象，先取出MyThreadScopeData的实例，再设置值
					MyThreadScopeData.getThreadDataInstance().setName(Thread.currentThread().getName());
					;
					MyThreadScopeData.getThreadDataInstance().setAge(data);
					new A().get(); // 调用模块一的方法
					new B().get(); // 调用模块二的方法
				}
			}).start();
		}
	}

	// 模块一
	static class A {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadDataInstance(); // 获取当前线程的共享数据对象
			System.out.println("A from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());

		}
	}

	// 模块二
	static class B {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadDataInstance(); // 获取当前线程的共享数据对象
			System.out.println("B from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());
		}
	}
}

/**
 * 定义共享数据结构类，类中定义ThreadLocal对象，将所有线程的共享数据都放置这个类的这个对象中
 */
class MyThreadScopeData {
	private String name;
	private int age;

	// private static MyThreadScopeData instance = null;
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

	// 步骤1、构建私有构造函数，不允许外部通过new创建
	private MyThreadScopeData() {
	};

	 // 步骤2、开放获取实例的方法
	public static MyThreadScopeData getThreadDataInstance() {
		MyThreadScopeData instance = map.get();
		if (null == instance) {
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}

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
