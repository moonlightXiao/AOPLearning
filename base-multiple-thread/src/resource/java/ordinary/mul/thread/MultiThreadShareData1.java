package resource.java.ordinary.mul.thread;

/**
 * ���̷߳��ʹ����������ݵķ�ʽ ��ʽһ������Ĵ�����һ����
 * 
 * @author xiao
 *
 */
public class MultiThreadShareData1 {
	public static void main(String[] args) {
		// ������
		ShareData1 data1 = new ShareData1();

		new Thread(data1).start();
		new Thread(data1).start();

	}
}

class ShareData1 implements Runnable {

	private int count = 100;

	@Override
	public void run() {
		while (count > 0) {
			count--;
		}
	}

}
