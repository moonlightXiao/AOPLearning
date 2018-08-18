package resource.java.ordinary.mul.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger Demo:�����߳�֮�����Ϣ����
 * ����ʵ��������֮������ݽ�����ÿ���������һ�������������Է��������ݣ���һ�����ó����ݵ��˽�һֱ�ȴ��ڶ������������ݵ���ʱ���ű˴˽�������
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
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ����� " + data1 + "������ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص�����Ϊ " + data2);

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
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ����� " + data1 + "������ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص�����Ϊ " + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
