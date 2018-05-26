package cn.base.learning.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用URL读取页面内容
 */
public class URLDemo2 {
	public static void main(String[] args) throws IOException {
		// 创建实例
		URL url = new URL("http://www.baidu.com");
		// 将URL对象所表示的资源字节输入流
		InputStream is = url.openStream();
		// 将字节输入流转换为字符输入流
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		// 为字符输入流添加缓冲
		BufferedReader br = new BufferedReader(isr);
		String data = br.readLine(); // 读取数据
		while (data!=null) { // 循环读取数据
			System.out.println(data); // 输出数据
			data = br.readLine();
		}
		br.close();
		isr.close();
		is.close();
	}
}
