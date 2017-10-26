package cn.exercise.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ProductService{

	public void saveProduct() {
		System.out.println("save product now ……");
	}

	public void deleteProduct() {
		System.out.println("delete product now ……");
	}
}
