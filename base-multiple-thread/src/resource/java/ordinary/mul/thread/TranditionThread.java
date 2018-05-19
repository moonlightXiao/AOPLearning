package resource.java.ordinary.mul.thread;

public class TranditionThread {
	public static void main(String[] args) {
		/*
		 * �����̵߳ķ���һ��
		 */
		Thread thread = new Thread(){
			@Override
			public void run() {
//				excuMethod(1);
			}
		};
		thread.start();
		
		/*
		 * �����̷߳�������
		 */
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				excuMethod(2);
			}
		});
		thread2.start();
		
		/*
		 * ��������Ľṹ�������ģ�
		 * 	new Thread( Runnable.run(){
		 * 		// ��ʶΪ3���߳�
		 *  }){ 
		 * 		run(){
		 * 		// ��ʶΪ4���߳�
		 * }}.start();
		 * ˼�����˷������е���excuMethod(3)��������excuMethod(4)����??
		 * 
		 * �𰸣����б�ʶΪ4���̡߳�
		 * ԭ����Thread.class�У�Thread��ʵ����Runnable�ӿڵġ���������Thread.start()������������������run()�������ҵ���������ķ������Ҳ������ø���ķ�����
		 * 		 �������У���ʶΪ4���߳����ڵ�run()�����Ѿ���д�˸���ķ����������������е���excuMethod(4)������
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				excuMethod(3);
			}
		}){
			@Override
			public void run() {
//				excuMethod(4);
			}
		}.start();
	}
	
	private static void excuMethod(int flag){
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(flag + "  " + Thread.currentThread().getName());
		}
	}
	
}
