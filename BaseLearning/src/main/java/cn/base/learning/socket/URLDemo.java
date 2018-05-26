package cn.base.learning.socket;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL常用方法
 */
public class URLDemo {
	public static void main(String[] args) throws MalformedURLException {
		// 创建URL实例
		URL url = new URL("http://www.baidu.com");
		URL url2 = new URL(url, "/index.html?uname=1#test"); // 在原有的URL资源上再创建URL实例，#后面表示锚点
		System.out.println("协议" + url2.getProtocol());
		System.out.println("主机" + url2.getHost());
		// 如果未指定端口号，则使用默认的端口号，此时getPort()返回-1
		System.out.println("端口" + url2.getPort());
		System.out.println("文件路径" + url2.getPath());
		System.out.println("文件名称：" + url2.getFile());
		System.out.println("相对路径" + url2.getRef());
		System.out.println("查询字符串" + url2.getQuery());
	}
}
