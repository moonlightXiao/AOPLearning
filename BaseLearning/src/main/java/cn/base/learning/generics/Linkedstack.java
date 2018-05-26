package cn.base.learning.generics;

/**
 * 内部链式存储 
 * @author Smile
 */
public class Linkedstack<T> {
	private static class Node<U>{
		U item;
		Node<U> next;
		Node(){
			item = null;
			next = null;
		}
		Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}
		boolean end(){
			return item == null && next == null;
		}
	}
	
	private Node<T> top = new Node<T>();
	
	public void push(T item){
		top = new Node<T>(item, top);
	}
	
	public T pop(){
		T result = top.item;
		if(!top.end())
			top = top.next;
		return result;
	}

	public static void main(String[] args) {
		Linkedstack<String> lss = new Linkedstack<String>();
		for (String s : "Phasers on stun!".split(" ")) {
			lss.push(s);
		}
		String s;
		while((s = lss.pop()) != null){
			System.out.println(s);
		}
	}
	
}


