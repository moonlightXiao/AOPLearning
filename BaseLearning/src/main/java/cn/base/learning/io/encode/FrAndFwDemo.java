package cn.base.learning.io.encode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndFwDemo {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("e://text.txt");
		FileWriter fw = new FileWriter("e://text.txt");
		char[] buffer = new char[2056];
		int c;
		while((c= fr.read(buffer, 0, buffer.length)) != 0){
			fw.write(buffer, 0, c);
			fw.flush();
		}
		fr.close();
		fw.close();
	}

}
