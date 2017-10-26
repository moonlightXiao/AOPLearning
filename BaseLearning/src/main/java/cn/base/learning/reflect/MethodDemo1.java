package cn.base.learning.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo1 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 要获取方法，先要获取类类型
		
		A a1 = new A();
		// 1、获取类类型
		Class c = a1.getClass();
		// 2、获取方法名，由名称和参数列表来决定
		Method m1 = c.getMethod("print", new Class[]{int.class, int.class});
		Method m2 = c.getMethod("print", new Class[]{String.class, String.class});
		Method m3 = c.getDeclaredMethod("test", new Class[]{int.class});
		Method m4 = c.getMethod("bMethod");
		
		// 方法的反射操作是指用Method对象来进行方法调用，如果方法没有返回类型，则返回nul，有则返回具体的值
		Object o1 = m1.invoke(a1, new Object[]{1,2});
		Object o2 = m2.invoke(a1, new Object[]{"AAa", "BBb"});	
		Object o4 = m4.invoke(a1);
		Object o3 = m3.invoke(a1, new Object[]{1});
	}
}

class A extends B{
	public void print(int a, int b) {
		System.out.println(a + b);
	}
	
	public void print(String a, String b){
		System.out.println(a.toLowerCase() + " " + b.toUpperCase());
	}
	
	private void test(int t){
		System.out.println("------ " + t + " -------");
	}
}

class B{
	public void bMethod(){
		System.out.println("------------- bMethod() ------------");
	}
}