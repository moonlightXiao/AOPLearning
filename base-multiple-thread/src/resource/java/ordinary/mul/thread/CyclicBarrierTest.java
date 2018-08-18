package resource.java.ordinary.mul.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier
 * Demo：模拟线程1、线程2、线程3以不同时间到达集合点1后，再一起以不同的速度到达集合点2，等所有线程都到达集合点2后，再一起出发到集合点3
 * 
 * @author Smile
 * 
 *         CyclicBarrier是一个同步工具类，它允许一组线程互相等待，直到到达某个公共屏障点。
 *         举例：整个公司的人利用周末时间到集体郊游一样，先各自从家里出发到公司集合，再同时出发到公园分开游玩，在餐馆集合后再同时开始聚餐。这里的“公司集合”、“餐馆集合”就是指的是公共屏障点。
 *         常用方法：
 *         （1）await()方法：在调用await()方法后，CyclicBarrier将阻塞这个线程并将它置入休眠状态等待其它线程的到来。
 * 
 *         与CountDownLatch不同的是该barrier在释放等待线程后可以重用，所以称它为循环（Cyclic）的屏障（Barrier）。
 */
public class CyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// 设置屏障，此屏障需要等待3个线程
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1， 当前已有"
								+ (cb.getNumberWaiting() + 1) + "个并发"
								+ ((2 == cb.getNumberWaiting()) ? "。线程已经全部到达集合点1,继续往下走！" : ""));
						cb.await(); // 设置第一次屏障，需等3个线程全部执行到此时，才继续往下

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2， 当前已有"
								+ (cb.getNumberWaiting() + 1) + "个并发"
								+ ((2 == cb.getNumberWaiting()) ? "。线程已经全部到达集合点2,继续往下走！" : ""));
						cb.await(); // 设置第二次屏障，需等3个线程全部执行到此时，才继续往下

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3， 当前已有"
								+ (cb.getNumberWaiting() + 1) + "个并发"
								+ ((2 == cb.getNumberWaiting()) ? "。线程已经全部到达集合点3,完毕！" : ""));
						cb.await(); // 设置第三次屏障，需等3个线程全部执行到此时，才继续往下

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runable);
		}
		service.shutdown();
	}
}
