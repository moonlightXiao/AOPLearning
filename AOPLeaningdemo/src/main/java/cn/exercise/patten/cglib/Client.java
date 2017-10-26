package cn.exercise.patten.cglib;

import cn.exercise.patten.proxy.RealSubject;
import cn.exercise.patten.proxy.Subject;
import net.sf.cglib.proxy.Enhancer;
/**
 * 客户端
 */
public class Client {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer(); // 传入目标对象，通过继承生成代理类
		enhancer.setSuperclass(RealSubject.class); // 织入代码
		enhancer.setCallback(new DemoMethodInterceptor());
		Subject subject = (Subject) enhancer.create();
		
		//调用方法
		subject.hello();
		subject.request();
	}
}
