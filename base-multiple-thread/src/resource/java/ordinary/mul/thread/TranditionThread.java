package resource.java.ordinary.mul.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程Demo
 * 
 * @author xiao
 */
public class TranditionThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * 创建线程的方法一：
		 */
		Thread thread = new Thread() {
			@Override
			public void run() {
				// excuMethod(1);
			}
		};
		thread.start();

		/*
		 * 创建线程方法二：
		 */
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// excuMethod(2);
			}
		});
		thread2.start();

		/*
		 * 创建线程方法三：
		 */
		// FutureTask是一个包装器，它通过接受Callable来创建，它同时实现了 Future和Runnable接口。
		FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
//				excuMethod(3);
				System.out.println("hi~~ 此处有个新线程");
				return "FutureTask 返回something";
			}
		});
		Thread t3 = new Thread(ft);
		t3.start();
		String result = ft.get();
		System.out.println(result);// 输出: FutureTask 返回something

		/*
		 * 问题：此方法运行的是excuMethod(4)方法还是excuMethod(5)方法??
		 * 
		思考：这个方法的结构是这样的： 
		new Thread( Runnable.run(){ 
			// 标识为4的线程 
		}){ run(){ 
			// 标识为5的线程 }
		}.start(); 
			
		 * 
		 * 答案：运行标识为5的线程。
		 * 原因：在Thread.class中，Thread是实现了Runnable接口的。在运行了Thread.start()方法后，
		 * 先在子类中找run()方法，找到则用子类的方法，找不到在用父类的方法。
		 * 在这题中，标识为5的线程所在的run()方法已经重写了父类的方法，所以最终运行的是excuMethod(5)方法。
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
//				excuMethod(4);
			}
		}) {
			@Override
			public void run() {
				// excuMethod(5);
			}
		}.start();
	}

	private static void excuMethod(int flag) {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(flag + "  " + Thread.currentThread().getName());
		}
	}

}
