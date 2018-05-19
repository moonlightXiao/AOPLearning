package resource.java.ordinary.mul.thread;

public class TranditionThread {
	public static void main(String[] args) {
		/*
		 * 创建线程的方法一：
		 */
		Thread thread = new Thread(){
			@Override
			public void run() {
//				excuMethod(1);
			}
		};
		thread.start();
		
		/*
		 * 创建线程方法二：
		 */
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				excuMethod(2);
			}
		});
		thread2.start();
		
		/*
		 * 这个方法的结构是这样的：
		 * 	new Thread( Runnable.run(){
		 * 		// 标识为3的线程
		 *  }){ 
		 * 		run(){
		 * 		// 标识为4的线程
		 * }}.start();
		 * 思考：此方法运行的是excuMethod(3)方法还是excuMethod(4)方法??
		 * 
		 * 答案：运行标识为4的线程。
		 * 原因：在Thread.class中，Thread是实现了Runnable接口的。在运行了Thread.start()方法后，先在子类中找run()方法，找到则用子类的方法，找不到在用父类的方法。
		 * 		 在这题中，标识为4的线程所在的run()方法已经重写了父类的方法，所以最终运行的是excuMethod(4)方法。
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				excuMethod(3);
			}
		}){
			@Override
			public void run() {
//				excuMethod(4);
			}
		}.start();
	}
	
	private static void excuMethod(int flag){
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(flag + "  " + Thread.currentThread().getName());
		}
	}
	
}
