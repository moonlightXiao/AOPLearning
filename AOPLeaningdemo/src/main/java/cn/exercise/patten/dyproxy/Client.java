package cn.exercise.patten.dyproxy;

import java.lang.reflect.Proxy;

import cn.exercise.patten.proxy.RealSubject;
import cn.exercise.patten.proxy.Subject;
/**
 * 客户端类
 */
public class Client {

	public static void main(String[] args) {
		Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), // 目标对象通过getClass方法获取类的所有信息后，调用getClassLoader()方法来获取类加载器。获取类加载器后，可以通过这个类型的加载器，在程序运行时，将生成的代理类加载到JVM中，以便运行时需要！
				new Class[]{Subject.class}, //获取被代理类的一组口信息，以便于生成的代理类可以具有代理类接口中的所有方法。
				new JdkProxySubject(new RealSubject()));//自定义InvocationHandler
		subject.request();
		subject.hello();
	}	

}
