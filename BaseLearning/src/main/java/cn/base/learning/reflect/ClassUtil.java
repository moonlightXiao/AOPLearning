package cn.base.learning.reflect;

import java.lang.reflect.Method;

public class ClassUtil {

	public static void main(String[] args) {
		int a = 0;
		printClassMessageObject(a);
	}

	/**
	 * 打印类的信息，包括类的成员函数、成员变量
	 * 
	 * @param obj
	 *            该对象所属类的信息
	 */
	public static void printClassMessageObject(Object obj) {
		// 要获取类的信息，首先要获取类的类类型
		Class c = obj.getClass(); // 获取对象所属类的类类型
		// 获取类的名称
		System.out.println("类的名称是：" + c.getName());
		/*
		 * 获取类的成员方法 getMethods()方法获取的是所有的public的函数，包括父类继承而来的
		 * getDeclaredMethods()方法获取的是所有该类自己声明的方法，不问访问权限
		 */
		Method[] ms = c.getMethods(); // Method[] ms1 = c.getDeclaredMethods();
		for (Method method : ms) {
			// 获取方法的返回值类型（实际上是返回值的类类型）
			Class returnType = method.getReturnType();
			System.err.print(returnType.getSimpleName() + " "); // 打印返回类型名称
			// 得到方法的名称
			System.out.print(method.getName() + " ( ");
			// 获取参数类型（实际上是参数列表的类类型）
			Class[] paramTypes = method.getParameterTypes();
			for (Class paramTypeClass : paramTypes) {
				// 打印参数类型的名称
				System.out.print(paramTypeClass.getSimpleName() + "  ");
			}
			System.out.println(")");
		}

	}
}
