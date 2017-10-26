package cn.base.learning.io.encode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BrAndBwOrPwDemo {
	
	// 对文件读写操作
	public static void main(String[] args) throws IOException {
		
		
		/**
		 * 通过Buffer来读写
		 */
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("e:/text.txt")));
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("e:/text.txt")));
		String line;
		while((line = br.readLine()) != null){
			System.out.println(line); // 一次读一行，不能识别换行
			bw.write(line);
			// 单独换行操作
			bw.newLine();
			bw.flush();
		}
		
		/**
		 * 通过Print来读写
		 */
		PrintWriter pw = new PrintWriter("e:/text.txt");
		while((line = br.readLine()) != null){
			pw.println(line);
			pw.flush();
		}
		
		br.close();
		bw.close();
		pw.close();
		
	}

}
