package cn.base.learning.io.encode;

import java.io.File;

/**
 * 遍历文件
 * @author Smile
 *
 */
public class FileUtils {
	
	public static void listDirectory(File file){
		if(!file.exists()){
			throw new IllegalArgumentException("文件不存在");
		}
		if(!file.isDirectory()){
			throw new IllegalArgumentException("不是目录");
		}
		
		// 遍历文件
//		String[] files = file.list();
//		for (int i = 0; i < files.length; i++) {
//			System.out.println(files[i]);
//		}
		
		File[] files2 = file.listFiles();
		if(null != files2 && files2.length > 0 ){
			for (File file3 : files2) {
				if(file3.isDirectory()){
					listDirectory(file3);
				} else{
					System.out.println(file3);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		listDirectory(new File("D:\\test"));
	}
}
