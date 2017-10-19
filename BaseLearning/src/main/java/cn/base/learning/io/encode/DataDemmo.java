package cn.base.learning.io.encode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class DataDemmo {
	public static void mainDataOutputStream() throws IOException{
		String file = "demo/doc.txt";
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
		
		dos.writeInt(10);
		dos.writeInt(-10);
		dos.writeLong(10L);
		dos.writeDouble(10.1);
		
		dos.writeUTF("南海"); // 以utf-8的形式
		dos.writeChars("你好"); // 以utf-16be的形式
		
		dos.close();
	}
	
	public static void mainDataInputStream(String file) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream("file"));
		
		int i = dis.readInt();
		
		System.out.println(i);
		i = dis.readInt();
		long lo = dis.readLong();
		System.out.println(lo);
		double d = dis.readDouble();
		System.out.println(d);
		String s = dis.readUTF();
		System.out.println(s);
		
		dis.close();
		
	}
}
