/**
 * 
 */
package resource.java.ordinary.mul.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量Demo
 * 
 * Semaphore可以维护当前访问自身的线程个数，并且提供了同步机制。使用Semaphore可以控制
 * 同时访问资源的线程个数。例如，实现一个文件允许的并发访问数：如现在只允许3个并发同时读取文件，但现在有10个线程需要访问，
 * 只有当那3个线程中任何一个访问完毕后，其他7个线程数中的其中一个才能去访问。
 * 
 * @author xiao
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// 创建信号量，数量为3
		final Semaphore sp = new Semaphore(3);
		// 开启10个线程
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// 获得信号量
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "当前已有" + (3 - sp.availablePermits() + "个并发"));
					try {
						// 模仿线程操作
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
					// 释放信号
					sp.release();
					System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (3 - sp.availablePermits() + "个并发"));
				}
			};
			// 执行线程
			service.execute(runnable);
		}
	}
}
