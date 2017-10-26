package cn.base.learning.io.encode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */
public class IOUtil {

	/**
	 * 读取指定文件内容，按照16进制输出到控制台
	 * @throws IOException 
	 */
	public static void printHex(String fileName) throws IOException{
		FileInputStream in = new FileInputStream(fileName);
		int b; 
		int i = 1;
		while((b = in.read()) != -1) {
			System.out.println(Integer.toHexString(b) + " ");
			if(i++%10 == 0){
				System.out.println();
			}
		}
		in.close();
	}
	
	/**
	 * 文件拷贝操作
	 * @param args
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File desFile) throws IOException {
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException("不是文件");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(desFile);
		
		/*
		 * 批量读写
		 */
		byte[] but = new byte[8 * 1024];
		int b;
		while((b = in.read(but,0, but.length)) != -1){
			out.write(but, 0, b);
			out.flush();
		}
		in.close();
		out.close();
		
	}
	
	/**
	 * 进行文件的拷贝
	 * @throws IOException
	 */
	public static void copyFileByBuffer(File srcFile, File desFile) throws IOException{
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException("不是文件");
		}
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
		
		int c;
		while ((c = bis.read())!=-1) {
			bos.write(c);
			bos.flush();//刷新缓冲区
		}

	}
	
	public static void main(String[] args) throws IOException {
		IOUtil.printHex("e:\\Text.java");
	}
}
