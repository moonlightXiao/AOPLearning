package cn.base.learning.io.encode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IsrAndOswDemo {

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("e:\\test.txt");
		InputStreamReader isr = new InputStreamReader(in);
		
		FileOutputStream out = new FileOutputStream("e:/text.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
		
		/**
		 * 单个字符读取
		 */
//		int c;
//		while((c = isr.read()) != -1){
//			System.out.println(c);
//		}
		
		/**
		 * 批量读取，放入Buffer这个字符数组中，从第0个位置开始放，最多放buffer.length个
		 * 返回读到字符的个数
		 */
		char[] buffer = new char[8*1024];
		int c;
		while((c = isr.read(buffer, 0, buffer.length)) != -1){
			String s = new String(buffer, 0, c);
			System.out.println(s);
			
			osw.write(buffer,0,c);
			osw.flush();
		}
		isr.close();
		osw.close();
	}

}
