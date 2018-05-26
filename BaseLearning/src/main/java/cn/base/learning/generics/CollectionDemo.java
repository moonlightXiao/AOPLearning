package cn.base.learning.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Collection排序
 */
public class CollectionDemo {
	
	public static void testSort1(){
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		Integer k;
		for (int i = 0; i < 10; i++) {
			do {
				k = random.nextInt(100);
			} while (list.contains(k));
			list.add(k);
		}
		System.out.print("排序前：");
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		System.out.println();
		Collections.sort(list);
		
		System.out.print("排序后：");
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
	}
	
	public static void testSort2(){
		List<String> list = new ArrayList<String>();
		list.add("dewer");
		list.add("bwerw");
		list.add("afgre");
		list.add("ccerwre");
		list.add("caerwre");
		list.add("ehhd");
		
		System.out.print("排序前：");
		for (String s : list) {
			System.out.print(s + " ");
		}
		System.out.println();
		Collections.sort(list);
		System.out.print("排序后：");
		for (String s : list) {
			System.out.print(s + " ");
		}
	}
	public static void testSort3(){
		List<String> list = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int len = (int) (Math.random()*10);
			StringBuffer sb = new StringBuffer();
			int ind = 0;
			do {
				ind++;
				sb.append(random.nextInt(10));
			} while (len != ind && i < 9);
			list.add(sb.toString());
		}
		
		System.out.print("排序前：");
		for (String s : list) {
			System.out.print(s + "  ");
		}
		System.out.println();
		Collections.sort(list);
		System.out.print("排序后：");
		for (String s : list) {
			System.out.print(s + "  ");
		}
	}
	
	public static void testSort4() {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(2, "a"));
		list.add(new Student(7, "b"));
		list.add(new Student(5, "c"));
		
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}
		
	}
	
	public static void main(String[] args) {
		testSort4();
	}
}
