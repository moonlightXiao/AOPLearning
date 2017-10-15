package cn.exercise.patten.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DemoMethodInterceptor implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("--- before ---");
		Object result = null;
		try {
			result = proxy.invokeSuper(obj, args);
		} catch (Exception e) {
			System.out.println("ex：" + e.getMessage());
		} finally {
			System.out.println("--- after ---");
		}
		return result;
	}

}
