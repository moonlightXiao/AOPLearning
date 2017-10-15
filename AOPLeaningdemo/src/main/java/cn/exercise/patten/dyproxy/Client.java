package cn.exercise.patten.dyproxy;

import java.lang.reflect.Proxy;

import cn.exercise.patten.proxy.RealSubject;
import cn.exercise.patten.proxy.Subject;

public class Client {

	public static void main(String[] args) {
		// 基于接口实现，第二个参数传接口进去
		Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
		subject.request();
		subject.hello();
	}	

}
