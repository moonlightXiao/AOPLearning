package resource.java.ordinary.mul.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger Demo:两个线程之间的信息交换
 * 用于实现两个人之间的数据交换，每个人在完成一定的事务后想与对方交互数据，第一个先拿出数据的人将一直等待第二个人拿着数据到来时，才彼此交换数据
 * 
 * @author xiao
 *
 */
public class ExchangerTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data1 = "XMSSS";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据 " + data1 + "交换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data1 = "GRSXX";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据 " + data1 + "交换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
