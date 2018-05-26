package cn.base.learning.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddress类的基本使用
 */
public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		/*
		 * 获取本地主机信息
		 */
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("主机名称" + address.getHostName());
		System.out.println("IP地址：" + address.getHostAddress());
		byte[] bytes = address.getAddress(); // 以获取字节数组的形式获取地址
		System.out.println("自己数组形式的ip: " + Arrays.toString(bytes));
		System.out.println(address);// 直接输出InetAddress对象
		System.out.println();
		
		/*
		 *  根据机器名获取InetAddresss实例
		 */
		InetAddress address2 = InetAddress.getByName("Moonlight");
		System.out.println("计算机名：" + address2.getHostName());
		System.out.println("IP地址：" + address2.getHostAddress());
		System.out.println();
		
		/*
		 * 根据IP获取InetAddress实例
		 */
		InetAddress address3 = InetAddress.getByName("192.168.1.102");
		System.out.println("计算机名：" + address3.getHostName());
		System.out.println("IP地址：" + address3.getHostAddress());
		System.out.println();
		
		/*
		 * 获取其它地址
		 */
		InetAddress address4 = InetAddress.getByName("baidu.com");
		System.out.println("计算机名：" + address4.getHostName());
		System.out.println("IP地址：" + address4.getHostAddress());
		System.out.println();
	}
}
