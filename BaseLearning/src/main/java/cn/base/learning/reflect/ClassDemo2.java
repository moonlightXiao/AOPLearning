package cn.base.learning.reflect;

public class ClassDemo2 {

	public static void main(String[] args) {
		/*
		 * 基本类型的类类型
		 */
		Class c1 = int.class; // int的类类型
		Class c2 = String.class; // String 的类类型
		Class c3 = double.class; // 
		Class c4 = Double.class;
		Class c5 = void.class;
		
		System.out.println(c1.getName());
		System.out.println(c1.getSimpleName());
	}

}
