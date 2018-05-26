package resource.java.ordinary.mul.thread;

import java.util.Random;

public class ThreadScopeShareData {
	
	private static int data = 0;
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				data =  new Random().nextInt();
				System.out.println(Thread.currentThread().getName() + "has put data:" + data);
				new A().get();
			}
		}).start();
	}
	
	static class A{
		public void get(){
			System.out.println("A from " + Thread.currentThread().getName() + "get data:" + data);
		}
	}
	
	static class B{
		public void get(){
			System.out.println("B from " + Thread.currentThread().getName() + "get data:" + data);
		}
	}
}
