package resource.java.ordinary.mul.thread;

public class Exercise {
	public static int j;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				j++;
			}
		}).start();
	}

}
