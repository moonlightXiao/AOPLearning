package cn.exercise.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

//	@Pointcut("within(cn.exercise..*)")
	@Pointcut("within(cn.exercise.service.impl.ProductService)")
	public void adminOnly() {

	}

	@Before("adminOnly()")
	public void before() {
		System.out.println("----------");
		System.out.println("@Aspect ###before");
		System.out.println("----------");
	}
}
