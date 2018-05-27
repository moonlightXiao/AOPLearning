package resource.java.ordinary.mul.thread;

import java.util.Random;

/**
 * �߳����ݹ���demo��ThreadLocal���߳��ڹ���������߳��������
 * 
 * ����һ���߳���ֻ�蹲��һ������
 * ��������ʹ��ThreadLocal
 */
public class ThreadLocalTest1 {

	// ����һ��ThreadLocal����
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt(); // ���ɵ�ǰ�̵߳�����
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					x.set(data); // ����ǰ�̵߳����ݷ���ThreadLocal
					new A().get(); // ����ģ��һ�ķ���
					new B().get(); // ����ģ����ķ���
				}
			}).start();
		}
	}

	// ģ��һ
	static class A {
		public void get() {
			int data = x.get();// ��ȡ��ǰ�̵߳�����
			System.out.println("A from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}

	// ģ���
	static class B {
		public void get() {
			int data = x.get();// ��ȡ��ǰ�̵߳�����
			System.out.println("B from " + Thread.currentThread().getName() + " get data:" + data);
		}
	}
}
