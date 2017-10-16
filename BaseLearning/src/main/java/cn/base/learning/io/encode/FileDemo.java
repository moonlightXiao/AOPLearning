package cn.base.learning.io.encode;

import java.io.File;
import java.io.IOException;

public class FileDemo {

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\test");
		if (!file.exists()) { // 判断文件是否存在
			file.mkdir(); // 创建文件
			System.out.println("创建文件");
		}else {
//			file.delete(); // 删除文件
			System.out.println("删除文件");
		}
		
		System.out.println(file.isDirectory()); // 是否为目录
		System.out.println(file.isFile()); // 是否为文件
		
		File file2 = new File("D:\\test\\exercise.txt"); 
		if(!file2.exists()) {
			file2.createNewFile(); // 创建文件
		}
		
		System.out.println(file);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(File.separator);
	}

	/*
	 * java.io.File用来表示文件（目录）我们就可以操作硬盘上的文件和目录。File类值用来表示用于表示文件（目录）的信息（名称，大小等），不能用于文件内容的访问。

	 * 总结File常用的API ：
	 	file.mkdir(); // 创建文件夹
		file.delete(); // 删除
		File.separator 设置分隔符,可移植到不同系统
		file.exists(); // 是否存在
		file.isDirectory(); // 是否目录
		file.isFile(); // 是否文件
		file.createNewFile(); // 创建文件
	 */
}
