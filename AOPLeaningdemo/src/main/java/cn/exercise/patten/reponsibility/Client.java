package cn.exercise.patten.reponsibility;

public class Client {
	static class HandlerA extends Handler {
		@Override
		protected void hanleProcess() {
			System.out.println("handle by a");
		}
	}
	
	static class HandlerB extends Handler {
		@Override
		protected void hanleProcess() {
			System.out.println("handle by b");
		}
	}
	
	static class HandlerC extends Handler {
		@Override
		protected void hanleProcess() {
			System.out.println("handle by c");
		}
	}
	
	public static void main(String[] args) {
		HandlerA handlerA = new HandlerA();
		HandlerB handlerB = new HandlerB();
		HandlerC handlerC = new HandlerC();
		
		handlerA.setSucessor(handlerB);
		handlerB.setSucessor(handlerC);
		
		handlerA.execute();
	}
}
