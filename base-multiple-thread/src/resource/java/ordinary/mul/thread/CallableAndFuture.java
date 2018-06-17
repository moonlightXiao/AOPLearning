package resource.java.ordinary.mul.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable��Future��ʹ��ʵ��
 * @author xiao
 *
 */
public class CallableAndFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		/*
		 * ��ȡĳһ�̵߳ķ��ؽ����
		 * CallbaleҪ����ExecutorService��submit���ύ�����ص�future�������ȡ������ Ҳ���Ի�ȡ�̵߳ķ��ؽ����
		 * Futureȡ�õĽ�����ͺ�Callable���صĽ�����ͱ���һ�£�����ͨ��������ʵ�ֵġ�
		 */
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello"; // ���ؽ�� 
			}
		});
		System.out.println("�ȴ������");
		System.out.println("�õ����" + future.get());// ��ȡ���
		
		/*
		 * ��ȡһ���̵߳ķ��ؽ����
		 * CompletionService�����ύһ��Callable������take�����Ż��Ѿ���ɵ�һ��Callable�����Ӧ��Future����
		 */
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for (int i = 1; i <= 10; i++) {
			final int seq = i;
			// ����ÿ���̵߳�����
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		// �õ������߳����еĽ��
		for (int i = 0; i < 10; i++) {
			System.out.println(completionService.take().get());
		}
		
	}
}
