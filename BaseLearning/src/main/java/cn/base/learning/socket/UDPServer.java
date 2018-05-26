package cn.base.learning.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务端
 * 基于UDP实现Socket通信
 */
public class UDPServer {

	public static void main(String[] args) throws IOException {
		/*
		 * 接收客户发送的数据
		 */
		// 1.创建服务器端DatagramSocket，指定端口
		DatagramSocket socket = new DatagramSocket(8800);
		// 2.创建数据报，用于接收客户端发送的数据
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		// 3.接收客户端发送的数据
		System.out.println("服务器已启动，等待接收数据报中……");
		socket.receive(packet); // 此方法在接收到数据报之前会一直阻塞
		// 4.读取数据
		String info = new String(data, 0, packet.getLength());
		System.out.println("服务器接收到了新信息：" + info);
		
		/*
		 * 向客户端响应数据
		 */
		// 1.定义
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] data2 = "服务器已经收到信息啦~".getBytes();
		// 2.创建数据报，包含响应的数据信息
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
		// 3.发送数据，返回給客户端
		socket.send(packet2);
		// 4.关闭资源
		socket.close();
	}

}
