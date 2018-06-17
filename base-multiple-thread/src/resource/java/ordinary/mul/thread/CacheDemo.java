package resource.java.ordinary.mul.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一个缓存demo，读写锁例子，实现读和写互斥、写和写互斥，但有可以多个并发的读，可提高系统性能
 * 
 * @author xiao
 */
public class CacheDemo {
	// 数据
	private Map<String, Object> cache = new HashMap<>();
	// 读写锁
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		// 上读锁，多个读时可以并发，不会造成对数据的破坏
		rwl.readLock().lock();
		Object value = null;
		try {
			value = cache.get(key);
			// 如果数据为空
			if (value == null) {
				// 释放读锁上写锁，上写锁后，数据不能被读
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					// 此处判断是为防止多个线程同时进入到这里时，多个线程对数据进行重复写
					if (value == null) {
						// 如果没有数据，则去查DB获取数据
						value = "XXX";
					}
				} finally {
					// 读取数据后释放写锁
					rwl.writeLock().unlock();
				}
				// 获取完数据后，恢复读锁，重新读取数据
				rwl.readLock().lock();
			}
		} finally {
			// 重新读取数据释放读锁
			value = cache.get(key);
			rwl.readLock().unlock();
		}
		return value;
	}
}
