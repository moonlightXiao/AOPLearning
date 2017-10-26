package cn.base.learning.io.encode;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/*
 * 读取文件练习
 */
public class RafDemo {
	public static void main(String[] args) throws IOException {
		File demo = new File("demo");
		if(!demo.exists()){
			demo.mkdir();
		}
		File file = new File(demo, "exercise.txt");
		if(!file.exists())
			file.createNewFile();
		
		// 读取文件(随机读取文件，好处是在文件下载快)
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//指针的位置
		System.out.println(raf.getFilePointer());
		
		raf.write('A');
		System.out.println(raf.getFilePointer());
		raf.write('B');
		
		int i= 0x7ffffff; // 最大的整数,用write()方法每次只能写一个字节，如果要把i全部写进去，要写4次
		raf.write(i >>> 24); // 右移动24位
		raf.write(i >>> 16);
		raf.write(i >>> 8);
		raf.write(i);
		System.out.println(raf.getFilePointer());
		
		raf.writeInt(i);  // 直接写整型
		
		String s = "中";
		byte[] gbk = s.getBytes("gbk");
		raf.write(gbk);
		System.out.println(raf.length());
		
		
		/*************   读文件      *************************/
		raf.seek(0);//移动指针到头部
		byte[] buf = new byte[(int) raf.length()];
		raf.read(buf);
		System.out.println(Arrays.toString(buf));
		String ss = new String(buf, "gbk");
		System.out.println(ss);  // 以字符串输出
		
		for (byte b : buf) {
			System.out.print(Integer.toHexString(b & 0xff) + " ");
		}
		
		raf.close(); // 读写完后一定要关闭！！
	}
}
