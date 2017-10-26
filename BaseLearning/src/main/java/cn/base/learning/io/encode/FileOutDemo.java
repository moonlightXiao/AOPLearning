/**
 * 
 */
package cn.base.learning.io.encode;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Smile
 *
 */
public class FileOutDemo {

	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("demo/out",true);
		
		out.write('A'); // 写入A的低8位
		out.write('B');

		int a = 10;
		out.write(a >>> 24);
		out.write(a >>> 16);
		out.write(a >>> 8);
		
		byte[] gbk = "你好".getBytes("gbk");
		out.write(gbk);
		out.close();
		
		IOUtil.printHex("demo/out.dat");
		
	}


}
