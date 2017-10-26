package cn.exercise.patten.proxy;

/**
 *	真实目标类 
 */
public class RealSubject implements Subject{

	@Override
	public void request() {
		System.out.println("RealSubject calls the method of request now ……");
	}

	@Override
	public void hello() {
		System.out.println("RealSubject calls the method of hello now ……");
	}

}
