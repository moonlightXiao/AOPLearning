/**
 * 
 */
package cn.exercise.patten.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.exercise.patten.proxy.RealSubject;

/**
 * 相当于AOP中的Aspect
 *
 */
public class JdkProxySubject implements InvocationHandler{
	
	private RealSubject realSubject;
	
	public JdkProxySubject(RealSubject realSubject) {
		this.realSubject = realSubject;
	}

	/**
	 * 相当于动态代理的逻辑 ，利用反射动态处理方法；（基于方法的方式，这是动态和静态代理的区别）
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("--- jdkProxy before ---");
		Object result = null;
		try {
			result = method.invoke(realSubject, args); // 利用反射调用目标对象的方法
		} catch (Exception e) {
			System.out.println("ex: " + e.getMessage());
		} finally {
			System.out.println("--- jdkProxy after ---");
			System.out.println();
		}
		return result;
	}

}
