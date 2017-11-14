package cn.base.learning.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 客户端
 * 基于UDP实现Socket通信
 */
public class UDPClient {

	public static void main(String[] args) throws IOException {
		/*
		 * 发送消息给服务端
		 */
		// 1.定义服务器的地址、端口号、数据报
		InetAddress address = InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data = "你好！我是客户端。".getBytes();
		// 2.创建数据报，包含发送的数据信息
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		// 3.创建DatagramSocket对象
		DatagramSocket socket = new DatagramSocket();
		// 4.向服务器端发送数据报
		socket.send(packet);
		
		/*
		 * 接收服务端响应
		 */
		// 1.创建数据报，用于接收服务器端响应的数据
		byte[] data2 = new byte[1024];
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
		// 2.接收服务器响应的数据
		socket.receive(packet2);
		// 3.读取数据
		String reply = new String(data2, 0, packet2.getLength());
		System.out.println("客户端接收到新信息：" + reply);
		// 4.关闭资源
		
	}

}
