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
 * Callable与Future的使用实例
 * @author xiao
 *
 */
public class CallableAndFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		/*
		 * 获取某一线程的返回结果：
		 * Callbale要采用ExecutorService的submit方提交，返回的future对象可以取消任务， 也可以获取线程的返回结果。
		 * Future取得的结果类型和Callable返回的结果类型必须一致，这是通过泛型来实现的。
		 */
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello"; // 返回结果 
			}
		});
		System.out.println("等待出结果");
		System.out.println("拿到结果" + future.get());// 获取结果
		
		/*
		 * 获取一组线程的返回结果：
		 * CompletionService用于提交一组Callable任务，其take方法放回已经完成的一个Callable任务对应的Future对象。
		 */
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for (int i = 1; i <= 10; i++) {
			final int seq = i;
			// 运行每个线程的任务
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		// 得到所有线程运行的结果
		for (int i = 0; i < 10; i++) {
			System.out.println(completionService.take().get());
		}
		
	}
}
