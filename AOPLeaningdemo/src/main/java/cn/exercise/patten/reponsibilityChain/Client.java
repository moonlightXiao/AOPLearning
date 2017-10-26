package cn.exercise.patten.reponsibilityChain;

import java.util.Arrays;
import java.util.List;

public class Client {
	static class ChainHandlerA extends ChainHandler{
		@Override
		protected void hanleProcess() {
			System.out.println("handle by a");
		}
	}
	static class ChainHandlerB extends ChainHandler{
		@Override
		protected void hanleProcess() {
			System.out.println("handle by b");
		}
	}
	static class ChainHandlerC extends ChainHandler{
		@Override
		protected void hanleProcess() {
			System.out.println("handle by c");
		}
	}
	
	public static void main(String[] args) {
		List<ChainHandler> handlers = Arrays.asList(
				new ChainHandlerA(),
				new ChainHandlerB(),
				new ChainHandlerC()		
		);
		
		Chain chain = new Chain(handlers);
		chain.proceed();
	}
}
