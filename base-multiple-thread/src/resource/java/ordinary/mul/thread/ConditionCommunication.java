package resource.java.ordinary.mul.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题目：子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着又回到主线程又循环100次，如此循环50次。写出程序
 * 
 * 经验：要用到共同数据（包括同步锁）或共同算法的若干个方法应该归在同一个类身上，这种设计体现了高类聚和程序的健壮性。
 * 
 * 采用condition通信方式：Condition的功能类似在传统线程技术中的Object.wait()和Object.notify的功能。
 * 在等待Condition时，允许发生“虚假唤醒”， 这通常作为对基础平台语义的让步。Condition允许多路等待的情况。
 */
public class ConditionCommunication {
	public static void main(String[] args) {

		final Business business = new Business();

		// 子线程循环
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					try {
						business.sub(i);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		// 主线程循环
		for (int i = 1; i <= 50; i++) {
			try {
				business.mian(i);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * 业务类型（包含各色的同步锁）
	 */
	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		// sub()方法是否该运行标识
		private boolean bShouldSub = true;

		/**
		 * 循环100次打印的方法sub()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void sub(int i) throws InterruptedException {
			lock.lock();
			try {
				while (!bShouldSub) { // 当 bShouldSub 为 false 时，则等待
					condition.await(); // 不能写成condition.wait();，这里的wait()是Object的方法
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread :   第" + i + "行， 第" + j + "列");
				}
				bShouldSub = false; // 执行for循环后，标志sub()方法不可再执行
				condition.signal(); // 发信号
			} finally {
				lock.unlock();
			}
		}

		/**
		 * 循环100次打印的方法mian()
		 * 
		 * @param i
		 * @throws InterruptedException
		 */
		public void mian(int i) throws InterruptedException {
			lock.lock();
			try {
				while (bShouldSub) {
					condition.await(); // 不能写成condition.wait();，这里的wait()是Object的方法
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread :   第" + i + "行， 第" + j + "列");
				}
				bShouldSub = true; // 执行for循环后，标志sub()方法可再执行了
				condition.signal(); // 发信号
			} finally {
				lock.unlock();
			}
		}
	}
}
