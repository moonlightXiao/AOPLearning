package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * �߳����ݹ���demo��ThreadLocal���߳��ڹ���������߳��������
 * 
 * ���������߳����蹲�������� 
 * ������������һ���������ݽṹ���࣬ThreadLocal������빲�����ݽṹ�����У��ڹ������ݽṹ���д洢�����̵߳Ĺ�������
 */
public class ThreadLocalTest3 {
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					// ���õ�ǰ�̵߳����ݶ�����ȡ��MyThreadScopeData��ʵ����������ֵ
					MyThreadScopeData.getThreadDataInstance().setName(Thread.currentThread().getName());
					;
					MyThreadScopeData.getThreadDataInstance().setAge(data);
					new A().get(); // ����ģ��һ�ķ���
					new B().get(); // ����ģ����ķ���
				}
			}).start();
		}
	}

	// ģ��һ
	static class A {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadDataInstance(); // ��ȡ��ǰ�̵߳Ĺ������ݶ���
			System.out.println("A from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());

		}
	}

	// ģ���
	static class B {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadDataInstance(); // ��ȡ��ǰ�̵߳Ĺ������ݶ���
			System.out.println("B from " + Thread.currentThread().getName() + ":  getName()=" + myData.getName()
					+ ", getAge()=" + myData.getAge());
		}
	}
}

/**
 * ���干�����ݽṹ�࣬���ж���ThreadLocal���󣬽������̵߳Ĺ������ݶ��������������������
 */
class MyThreadScopeData {
	private String name;
	private int age;

	// private static MyThreadScopeData instance = null;
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

	// ����1������˽�й��캯�����������ⲿͨ��new����
	private MyThreadScopeData() {
	};

	 // ����2�����Ż�ȡʵ���ķ���
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
