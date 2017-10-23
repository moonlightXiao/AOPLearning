package cn.base.learning.io.encode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 对象序列化 
 */
public class OjectSerializeDemo {
	public static void main(String[] args) throws IOException, IOException, Exception {
		String file = "demo/obj.dat";
		/**
		 * 1. 对象的序列化
		 */
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//		Student stu = new Student("xiao", 20);
//		oos.writeObject(stu);
//		oos.flush();
//		oos.close();
		
		/**
		 * 反序列化
		 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Student stu2 = (Student) ois.readObject();
		System.out.println(stu2);
		ois.close();
	}
}
