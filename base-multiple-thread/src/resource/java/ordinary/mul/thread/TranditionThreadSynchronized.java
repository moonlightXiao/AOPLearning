package resource.java.ordinary.mul.thread;
/**
 * 线程互斥：synchronized
 * @author admin
 *
 */
public class TranditionThreadSynchronized {
	public static void main(String[] args) {
		new TranditionThreadSynchronized().init();
	}

	public void init() {
		final Output ootput = new Output();
		
		// 第一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						ootput.output2("xiao");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// 第二个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						ootput.output2("hag");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	class Output {
		public void output(String name) {
			int len = name.length();
			// synchronized(XXX){}中，XXX表示的是要锁住的对象
			// 此this指的是和output2中实现的效果一样，锁的是Output对象
			synchronized (this) {
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}

		public synchronized void output2(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
			// 此时的内部类需要标识为static class，表示为外部类。
			// 如果此时需要和output1()方法保持一致，则需要将synchronized (this)修改为synchronized (Output.class)
	//		public static synchronized void output3(String name) {
	//			int len = name.length();
	//			for (int i = 0; i < len; i++) {
	//				System.out.print(name.charAt(i));
	//			}
	//			System.out.println();
	//		}
	}
}
