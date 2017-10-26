package cn.base.learning.keyword;

/*
 * 通过this关键字调用关键字
 */
public class FlowerThis {
	int count = 0;
	String s = "null";
	
	public FlowerThis(int countFlower) {
		count = countFlower;
		System.out.println("int cnstructor");
	}
	
	public FlowerThis(String ss) {
		s = ss;
		System.out.println("String cnstructor");
	}
	
	public FlowerThis(String ss, int countFlower) {
		this(countFlower);
//		this(ss); // //z只能通过它来掉一个构造函数，且要放在最前面
		this.s = ss;
		System.out.println("String and int cnstructor");
	}
}
