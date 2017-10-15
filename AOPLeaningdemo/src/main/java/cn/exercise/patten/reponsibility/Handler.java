package cn.exercise.patten.reponsibility;

public abstract class Handler {
	
	private Handler sucessor;
	
	public void execute() {
		hanleProcess();
		if(null != sucessor){
			sucessor.execute();
		}
	}
	
	public Handler getSucessor() {
		return sucessor;
	}

	public void setSucessor(Handler sucessor) {
		this.sucessor = sucessor;
	}
	
	protected abstract void hanleProcess();
}
