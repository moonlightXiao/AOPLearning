package cn.base.learning.io.encode;

public class Encode {
	public static void main(String[] args) throws Exception {
		String s = "特别ABC";
		byte[] byte1 = s.getBytes(); // 转化为字节序列，用项目默认的字节码（UTF-8）

		for (byte b : byte1) {
			// 把字节（转换成int）以16进制的方式显示, b & 0xff表示去掉前6位
			System.out.print(Integer.toHexString(b & 0xff) + " "); // 运行观察可得知，UTF8编码中文展3个字节，英文站1个字节
		}

		System.out.println();
		
		byte[] byte2 = s.getBytes("gbk"); // 转化为字节序列，用gbk编码
		for (byte b : byte2) {
			// 把字节（转换成int）以16进制的方式显示, b & 0xff表示去掉前6位
			System.out.print(Integer.toHexString(b & 0xff) + " ");  // 运行观察可得知，gbk编码中文展2个字节，英文站1个字节
		}
		

		System.out.println();
		
		// java是双字节编码 -- utf-16be，中文占2个字节，英文展2个字节
		byte[] byte3 = s.getBytes("utf-16be"); // 转化为字节序列，用gbk编码
		for (byte b : byte3) {
			// 把字节（转换成int）以16进制的方式显示, b & 0xff表示去掉前6位
			System.out.print(Integer.toHexString(b & 0xff) + " ");  // 运行观察可得知，gbk编码中文展2个字节，英文站1个字节
		}
		
		System.out.println();
		/*O
		 * 当字节序列（如byte数组中内容）为某种编码时，这时把字节序列转为字符串时，也要对应使用某种编码方式，否则会出错，如下例子
		 */
		// byte2中的字节原本为gbk，现在转化为UTF-8编码，则为会出现乱码
		String str = new String(byte2, "utf-8");
		System.out.println(str);
		
		/*
		 * 另外：文本文件 就是字节序列，可以是任意编码的字节序列，如果我们在中文机器上直接创建文本文件，那么该文本文件只认识ansi编码
		 */
	}
}
