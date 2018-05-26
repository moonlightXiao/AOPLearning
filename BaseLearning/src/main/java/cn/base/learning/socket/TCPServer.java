package cn.base.learning.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 基于TCP协议的Socket通信 -- 单线程
 */
public class TCPServer {
	
	public static void main(String[] args) {
		try {
			// 1.创建一个服务器端Socket，即ServerSocket,指定绑定的端口，并监听
			ServerSocket serverSocket = new ServerSocket(8888);
			// 2.调用accept()方法开始监听，等待客户端的连接
			System.out.println("服务器即将启动，等待客户端的连接***");
			Socket socket = serverSocket.accept();
			// 3.获取输入流，并读取客户端信息
			InputStream is = socket.getInputStream();
			InputStreamReader irs = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(irs);
			String info = null;
			while((info=br.readLine()) != null){
				System.out.println("服务器接收到消息：" + info);
			}
			socket.shutdownInput();//关闭输入流
			// 4.获取输出流，响应客户端请求
		    OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);// 包装为打印流
			pw.write("终于等到你！");
			pw.flush();
			
			// 5.关闭资源
			pw.close();
			os.close();
			br.close();
			irs.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
