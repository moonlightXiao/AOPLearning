package resource.java.ordinary.mul.thread;

/**
 * 面试题目：子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着又回到主线程又循环100次，如此循环50次。写出程序
 * 
 * 经验：要用到共同数据（包括同步锁）或共同算法的若干个方法应该归在同一个类身上，这种设计体现了高类聚和程序的健壮性。
 *
 */
public class TranditionThreadCommunication {
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
}

/**
 * 业务类型（包含各色的同步锁）
 */
class Business {
	// sub()方法是否该运行标识
	private boolean bShouldSub = true;

	/**
	 * 循环100次打印的方法sub()
	 * 
	 * @param i
	 * @throws InterruptedException
	 */
	public synchronized void sub(int i) throws InterruptedException {
		while (!bShouldSub) { // 当 bShouldSub 为 false 时，则等待
			this.wait();
		}
		for (int j = 1; j <= 10; j++) {
			System.out.println("sub thread :   第" + i + "行， 第" + j + "列");
		}
		bShouldSub = false; // 执行for循环后，标志sub()方法不可再执行
		this.notify(); // 唤醒线程
	}

	/**
	 * 循环100次打印的方法mian()
	 * 
	 * @param i
	 * @throws InterruptedException
	 */
	public synchronized void mian(int i) throws InterruptedException {
		while (bShouldSub) {
			this.wait();
		}
		for (int j = 1; j <= 100; j++) {
			System.out.println("main thread :   第" + i + "行， 第" + j + "列");
		}
		bShouldSub = true; // 执行for循环后，标志sub()方法可再执行了
		this.notify(); // 唤醒线程
	}
}
