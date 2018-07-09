package resource.java.ordinary.mul.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题目：线程1循环10次，接着线程2循环20次，接着线程3循环30次，又回到线程1循环10次，接着又回到线程2循环20次……，如此循环50次。写出程序
 * 
 * 经验：要用到共同数据（包括同步锁）或共同算法的若干个方法应该归在同一个类身上，这种设计体现了高类聚和程序的健壮性。
 * 
 * 采用condition通信方式：Condition的功能类似在传统线程技术中的Object.wait()和Object.notify的功能。
 * 在等待Condition时，允许发生“虚假唤醒”， 这通常作为对基础平台语义的让步。Condition可实现多路等待的情况。
 */
public class ConditionCommunication2 {
	public static void main(String[] args) {

		final Business business = new Business();

		// 线程2循环sub2()
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub2(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// 线程3循环sub3()
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub3(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// （主线程）线程1循环sub1()
		for (int i = 1; i <= 50; i++) {
			try {
				business.sub1(i);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * 业务类型（包含各色的同步锁）
	 */
	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		// sub()方法是否该运行标识
		private int shouldSub = 1;

		/**
		 * 循环10次打印的方法sub1()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub1(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 1) {  // 当 shouldSub 不为 1 时，则等待
					condition1.await(); // 不能写成condition.wait();，这里的wait()是Object的方法
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub1 thread :   第" + i + "行， 第" + j + "列");
				}
				shouldSub = 2; // 执行for循环后，标志下一个可以循环的方法时sub2()
				condition2.signal(); // condition2发信号
			} finally {
				lock.unlock();
			}
		}

		/**
		 * 循环20次打印的方法sub2()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub2(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 2) {  // 当 shouldSub 不为 2 时，则等待
					condition2.await(); // 不能写成condition.wait();，这里的wait()是Object的方法
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub2 thread :   第" + i + "行， 第" + j + "列");
				}
				shouldSub = 3; // 执行for循环后，标志下一个可以循环的方法时sub3()
				condition3.signal(); // condition3发信号
			} finally {
				lock.unlock();
			}
		}

		/**
		 * 循环30次打印的方法sub3()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub3(int i) throws InterruptedException {
			lock.lock();
			try {
				while (shouldSub != 3) {  // 当 shouldSub 不为 3时，则等待
					condition3.await(); // 不能写成condition.wait();，这里的wait()是Object的方法
				}
				for (int j = 1; j <= 30; j++) {
					System.out.println("sub3 thread :   第" + i + "行， 第" + j + "列");
				}
				shouldSub = 1; // 执行for循环后，标志下一个可以循环的方法时sub1()
				condition1.signal(); // condition1发信号
			} finally {
				lock.unlock();
			}
		}

	}
}
