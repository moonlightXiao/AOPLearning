package cn.base.learning.io.encode;

import java.io.Serializable;

public class Student implements Serializable {
	private String name;
	private transient int age; // 该元素不会进行jvm默认的序列化

	public Student() {

	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	/**
	 * 自定义序列化
	 */
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		// 把jvm能默认序列化的元素都进行序列化当中
		s.defaultWriteObject();
		// 自己完成stuage的序列化
		s.writeInt(age);
	}

	/**
	 * 自定义反序列操作
	 */
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		// 把jvm能返序列化的元素都先反序列化
		s.defaultReadObject();
		this.age = s.readInt(); // 自己完成反序列
	}
}
