package resource.java.ordinary.mul.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queqe3 q3 = new Queqe3();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						q3.get();
					}
				}
			}){
			}.start();

			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						q3.put(new Random().nextInt(10000));
					}
				}
			}){
			}.start();
		}
	}
}

class Queqe3{
	private Object data = null; // 共享数据，只能有一个线程写这个数据，但可以多个线程同时读
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to get data…");
			Thread.sleep((long)Math.random()*1000);
			System.out.println(Thread.currentThread().getName() + " is had get the data:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	// 写
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to write data…");
			Thread.sleep((long)Math.random()*1000);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " is had write the data:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}
}

class Queqe2{
	private Object data = null; // 共享数据，只能有一个线程写这个数据，但可以多个线程同时读
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(); // 这种锁，不管读和写都会锁住
	// 读
	public void get(){
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName() + " is ready to get data…");
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is had get the data:" + data);
		rwl.readLock().unlock();
	}
	
	// 写
	public void put(Object data){
		rwl.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + " is ready to write data…");
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " is had write the data:" + data);
		rwl.writeLock().unlock();
	}
}