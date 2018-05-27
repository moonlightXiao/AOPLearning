package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * �߳����ݹ���demo��ThreadLocal���߳��ڹ���������߳��������
 * 
 * ���������߳����蹲��������
 * ����һ������һ���������ݽṹ���࣬���������ݽṹ����ΪThreadLocal�ķ���
 */
public class ThreadLocalTest2 {

	// ����һ��ThreadLocal���󣬲���MyThreadScopeData1��λ��ֵ����
	private static ThreadLocal<MyThreadScopeData1> myThreadScopeData = new ThreadLocal<MyThreadScopeData1>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					// ���õ�ǰ�̵߳����ݶ��󣬲��ҽ����������ThreadLocal������
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

	// ģ��һ
	static class A {
		public void get() {
			MyThreadScopeData1 myData = myThreadScopeData.get(); // ��ȡ��ǰ�߳����ݶ���
			System.out.println("A from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());

		}
	}

	// ģ���
	static class B {
		public void get() {
			MyThreadScopeData1 myData = myThreadScopeData.get(); // ��ȡ��ǰ�߳����ݶ���
			System.out.println("B from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());
		}
	}
}

/**
 * ���干�����ݽṹ��
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
