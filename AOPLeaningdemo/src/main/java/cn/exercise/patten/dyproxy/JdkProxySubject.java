/**
 * 
 */
package cn.exercise.patten.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.exercise.patten.proxy.RealSubject;

/**
 *	自定义InvocationHandler
 */
public class JdkProxySubject implements InvocationHandler{
	
	private RealSubject realSubject; // 目标对象
	
	public JdkProxySubject(RealSubject realSubject) {
		this.realSubject = realSubject;
	}
	
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
