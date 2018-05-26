package cn.base.learning.generics;

/**
 * 通用类生成器
 * @author Smile
 */
public class BaseGenerator<T> implements Generator<T>{
	
	private Class<T> type;
	
	public BaseGenerator(Class<T> type){
		this.type = type;
	}

	@Override
	public T next() throws Exception {
		return type.newInstance();
	}
	
	public static <T> Generator<T> create(Class<T> type){
		return new BaseGenerator<T>(type);
	}

}
