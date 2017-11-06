package cn.base.learning.generics;

public class FiveTuple<A, B, C, D, E> {
	
	public FiveTuple(A a, B b, C c, D d, E e){
		System.out.println(a + " " + b + " " + c + " " + d);
	}
	
	public static void main(String[] args) {
		FiveTuple<String,Integer,Character,Double,Long> five = 
				new FiveTuple<String, Integer, Character, Double, Long>(new String("a"), new Integer(2), new Character('c'), new Double("20"), new Long(3L));
	}
	
}
