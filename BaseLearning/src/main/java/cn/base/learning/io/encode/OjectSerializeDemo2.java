package cn.base.learning.io.encode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象序列化 (子类、父类)
 */
public class OjectSerializeDemo2 {
	public static void main(String[] args) throws IOException, IOException, Exception {
		/**
		 * 对象序列化
		 */
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo/obj1.dat"));
//		Foo2 foo2 = new Foo2();
//		oos.writeObject(foo2);
//		oos.flush();
//		oos.close();
		
		/**
		 * 反序列化是否递归调用父类的构造函数
		 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo/obj1.dat"));
		Foo2 foo2 = (Foo2) ois.readObject();
		System.out.println(foo2);
		ois.close();
	}
	
	
}
 
class Foo implements Serializable{
	public Foo(){
		System.out.println("foo……");
	}
}

class Foo1 extends Foo{
	public Foo1(){
		System.out.println("foo1……");
	}
}

class Foo2 extends Foo1{
	public Foo2(){
		System.out.println("foo2……");
	}
}