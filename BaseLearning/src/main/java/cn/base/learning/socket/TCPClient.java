package cn.base.learning.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 * 基于TCP协议的Socket通信
 */
public class TCPClient {
	public static void main(String[] args) {
		try {
			// 1.创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 8888);
			
			// 2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();//字节输出流
			PrintWriter pw = new PrintWriter(os);
			pw.write("客户端发信息来啦~~");
			pw.flush();
			socket.shutdownOutput();// 关闭输出流
			
			// 3.获取输入流，并读取服务器端的响应信息
			InputStream is =  socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info=br.readLine()) != null){
				System.out.println("客户端接收到信息：" + info);
			}
			
			// 4.关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
