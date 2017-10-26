package cn.base.learning.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	public static void main(String[] args) {
		int a = 0;
		printClassMethodObject(a);
	}

	/**
	 * 打印类的成员函数信息
	 * 
	 * @param obj
	 *            该对象所属类的信息
	 */
	public static void printClassMethodObject(Object obj) {
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

	/**
	 * 打印类的成员方法信息
	 * @param obj
	 */
	public static void printClassFieldMessage(Object obj) {
		Class c = obj.getClass();
		/*
		 * 获取成员变量
		 * Field类封装了关于成员变量的操作
		 * getFields()方法获取的是所有的public的成员变量信息
		 * getDeclaredFields()方法获取的是该类自己声明的成员变量的信息
		 */
		Field[] fs = c.getDeclaredFields();
		for (Field field : fs) {
			// 得到成员变量的类型的类类型
			Class fieldType = field.getType();
			// 得到成员变量类类型的名称
			String typeName = fieldType.getName();
			// 得到成员变量的名称
			String fieldName = field.getName();
			System.out.println(typeName + " " + fieldName);
		}
	}
	
	/**
	 * 打印对象的构造函数的信息
	 */
	public static void printClassConMessage(Object obj) {
		Class c = obj.getClass();
		/*
		 * Constructor中封装了构造函数的信息
		 * getConstructors() 获取所有的public的构造函数
		 * getDeclaredConstructors() 获取所有的public的构造函数
		 */
		Constructor[] cs = c.getConstructors();
		for (Constructor constructor : cs) {
			System.out.println(constructor.getName() + " (");
			// 获取构造函数的参数列表(参数列表的类类型)
			Class[] paramType = constructor.getParameterTypes();
			for (Class class1 : paramType) {
				System.out.print(class1.getName() + " " );
			}
			System.out.println(")");
		}
	}
}
