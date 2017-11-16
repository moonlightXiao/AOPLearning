package cn.base.learning.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 基于TCP协议的Socket通信 -- 多线程实现多客户的通信
 */
public class TCPServerMulConnection {
	public static void main(String[] args) {
		try {
			// 创建一个服务器端Socket，即ServerSocket,指定绑定的端口，并监听
			ServerSocket serverSocket = new ServerSocket(8888);
			// 调用accept()方法开始监听，等待客户端的连接
			System.out.println("服务器即将启动，等待客户端的连接***");
			Socket socket = null;
			int count = 0; // 记录客户端的连接
			// 循环监听等待客户端的连接
			while (true) {
				// 调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				// 创建一个新的线程
				ServerThreadHelper serverThread = new ServerThreadHelper(socket);
				//serverThread.setPriority(4); //可设置线程优先级（优先级默认为5，数字越小，优先级越低；未设置优先级可能导致运行时速度非常慢，可适当降低）
				// 启动线程
				serverThread.start();
				count++; // 统计客户端的数量
				InetAddress address = socket.getInetAddress();
				System.out.println("第 " + count + " 个客户端连接成功！此客户端的ip为：" + address.getHostAddress());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


/**
 * 服务器端线程处理类
 */
class ServerThreadHelper extends Thread{
	// 和本线程相关的socket
	Socket socket = null;
	
	public ServerThreadHelper(Socket socket){
		this.socket = socket;
	}
	
	// 线程执行的操作，响应客户端请求
	public void run(){
		
		InputStream is = null;
		InputStreamReader irs = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			// 1.获取输入流，并读取客户端信息
			is = socket.getInputStream();
			irs = new InputStreamReader(is);
			br = new BufferedReader(irs);
			String info = null;
			while((info=br.readLine()) != null){
				System.out.println("服务器接收到消息：" + info);
			}
			socket.shutdownInput();//关闭输入流
			// 2.获取输出流，响应客户端请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("终于等到你！");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 3.关闭资源
			try {
				if (pw!=null) {
					pw.close();
				}
				if (os!=null) {
					os.close();
				}
				if (br!=null) {
					br.close();
				}
				if (irs!=null) {
					irs.close();
				}
				if (is!=null) {
					is.close();
				}
				if (socket!=null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
