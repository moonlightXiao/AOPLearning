package cn.exercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.exercise.service.impl.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopLeaningdemoApplicationTests {

	@Autowired
	private ProductService productService;
	
	@Test
	public void testAOP() {
		productService.saveProduct();
		productService.deleteProduct();
	}

}
