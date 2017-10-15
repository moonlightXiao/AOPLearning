package cn.base.learning.keyword;

/*
 * 通过this关键字，将自身传递给外部方法
 */
public class PassingThis {
	public static void main(String[] args) {
		new Person().eat(new Apple());
	}
}

class Person {
	public void eat(Apple apple) {
		Apple peeled = apple.getPeeled();
		System.out.println("good");
	}
}

class Peeler {
	static Apple peel(Apple apple){
		return apple;
	}
}

class Apple {
	Apple getPeeled() {
		return Peeler.peel(this);
	}
}