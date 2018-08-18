package resource.java.ordinary.mul.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch demo ：模拟裁判发出命令后，3个运动员以不同的速度奔跑。等3个运动员都跑完后，裁判得到比赛结果
 * 
 * @author Smile
 *
 *         CountDownLatch可以理解为一个计数器在初始化时设置初始值，当一个线程需要等待某些操作先完成时，需要调用await()方法。这个方法让线程进入休眠状态直到等待的所有线程都执行完成。每调用一次countDown()方法内部计数器减1,当计数达到0时，则所有的等待着开始执行。
 *         可以实现一个线程（也可以是多个线程）等待其他线程来唤醒，也可以实现一个线程通知多个线程的效果。
 *         举例：类似裁判一声口令下，所有的运动员才能开始奔跑，或者所有的运动员奔跑完后才有比赛结果。
 * 
 *         核心方法两个：countDown()和await()
 *         (1)countDown():使CountDownLatch维护的内部计数器减1,每个被等待的线程完成的时候调用
 *         (2)await():线程在执行到CountDownLatch的时候会将此线程置于休眠
 */
public class CountdownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		// 创建一个“命令”计数器
		final CountDownLatch cdOrder = new CountDownLatch(1);
		// 创建三个“回应”计数器
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "正在准备接收命令");
						// 等待“命令”计数器为0
						cdOrder.await();

						System.out.println("线程" + Thread.currentThread().getName() + "已收命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "处理结束！回应命令处理结果");
						// “回应”计数器减1
						cdAnswer.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};
			service.execute(runnable);
		}

		// 主线程发命令CountDownLatch cdOrder命令
		Thread.sleep((long) (Math.random() * 10000));
		System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
		// “命令”计数器减1，“命令”计数器为0，开始唤醒cdOrder.await()的线程
		cdOrder.countDown();
		System.out.println("线程" + Thread.currentThread().getName() + "已发布命令，等待处理结果……");

		// 等待“回应”计数器为0时，再继续执行
		cdAnswer.await();
		System.out.println("线程" + Thread.currentThread().getName() + "已收到全部结果结果。");

		service.shutdown();
	}

}
