package resource.java.ordinary.mul.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用实例
 * 
 * @author xiao
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newFixedThreadPool(3); // 创建固定大小的线程池：起3个线程的线程池（固定线程数的线程池）
//		ExecutorService threadPool = Executors.newCachedThreadPool(); // 创建缓存线程池：线程池的线程数目随实际运行线程的数目变化而变化
		ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 创建单一线程池：线程池中只有一个线程
		for (int i = 0; i < 10; i++) {
			final int task = i; // 需要将task变量设置为final，这样在线程的runnable中才能访问
			threadPool.execute(new Runnable() { // 每个runnable表示一个任务
				@Override
				public void run() {
					for (int i = 1; i <= 10; i++) { // 此循环中的i和上面循环的i并不是同一个方法中的变量，所以不会冲突
						System.out.println(Thread.currentThread().getName() + " is loop of " + i + " task for " + task);
					}
				}
			});
		}
		System.out.println("10 tasks have committed! ");
		threadPool.shutdown();// 线程池中无线程运行时，则关闭线程池

		/**
		 * 用线程池启动定时器：调用 ScheduledExecutorServiced的schedule方法，
		 * 返回的ScheduledFuture对象可以取消任务支持间隔重复任务的定时方式，
		 * 不直接支持绝对定时方式，需要转换成相对时间方式
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程池定时器");
			}
		}, 6, 1, TimeUnit.SECONDS);
	}
}
