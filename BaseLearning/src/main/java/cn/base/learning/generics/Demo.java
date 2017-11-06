package cn.base.learning.generics;

public class Demo {
	public static void main(String[] args) throws Exception {
		Generator<CountObject> gen = BaseGenerator.create(CountObject.class); // 利用生成器生成CountObject对象
		for (int i = 0; i < 5; i++) {
			System.out.println(gen.next());
		}
	}
}
