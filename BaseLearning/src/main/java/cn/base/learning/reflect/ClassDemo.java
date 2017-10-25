package cn.base.learning.reflect;

public class ClassDemo {
	Foo foo1 = new Foo();
	/*
	 * java中万物都为对象，类也是对象，是Class类的实例对象，这个对象我们称为该类的类类型
	 * 任何类都是Class的实例对象，这个实例对象有三种表示方法
	 * Foo这个类就是一个实例对象，Class类的实例对象
	 */
	//第一种（实际也在告诉我们，任何一个类都有一个隐含的静态成员变量class）
	Class c1 = Foo.class;
	
	// 第二种（已知该类的对象，通过getClass()方法）
	Class c2 = foo1.getClass();
	
	// 第三种
	Class c3 = null;
	
	private void test() throws InstantiationException, IllegalAccessException{
		try {
			c3 = Class.forName("");
		} catch (Exception e) {
		}
		
	// 通过类类型创建类的对象实例
		c1.newInstance();
	}
	
	

}

class Foo{}