package cn.exercise.service.impl;

import org.springframework.stereotype.Service;

import cn.exercise.service.IProductService;

@Service
public class ProductService{

	public void saveProduct() {
		System.out.println("save product new ……");
	}

	public void deleteProduct() {
		System.out.println("delete product new ……");
	}
}
