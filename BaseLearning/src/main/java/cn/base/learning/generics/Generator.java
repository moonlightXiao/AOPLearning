package cn.base.learning.generics;

public interface Generator<T> {
	T next() throws Exception;
}
