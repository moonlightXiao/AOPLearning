package cn.exercise.patten.proxy;

public class Proxy implements Subject{

	private RealSubject realSubject;
	
	public Proxy(RealSubject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public void request() {
		System.out.println(" --- before ---");
		try {
			realSubject.request(); // 真正目标对象代理方法
		} catch (Exception e) {
			System.out.println("ex: " + e.getMessage());
		} finally {
			System.out.println(" --- after ---");
		}
	}

	@Override
	public void hello() {
		realSubject.hello();
	}

}
