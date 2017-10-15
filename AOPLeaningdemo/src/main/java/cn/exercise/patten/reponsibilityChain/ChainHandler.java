package cn.exercise.patten.reponsibilityChain;

public abstract class ChainHandler {
	
	
	public void execute(Chain chain) {
		hanleProcess();
		chain.proceed();
	}
	
	protected abstract void hanleProcess();
}
