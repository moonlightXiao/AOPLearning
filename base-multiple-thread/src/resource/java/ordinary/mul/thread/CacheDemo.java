package resource.java.ordinary.mul.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * һ������demo����д�����ӣ�ʵ�ֶ���д���⡢д��д���⣬���п��Զ�������Ķ��������ϵͳ����
 * 
 * @author xiao
 */
public class CacheDemo {
	// ����
	private Map<String, Object> cache = new HashMap<>();
	// ��д��
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		// �϶����������ʱ���Բ�����������ɶ����ݵ��ƻ�
		rwl.readLock().lock();
		Object value = null;
		try {
			value = cache.get(key);
			// �������Ϊ��
			if (value == null) {
				// �ͷŶ�����д������д�������ݲ��ܱ���
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					// �˴��ж���Ϊ��ֹ����߳�ͬʱ���뵽����ʱ������̶߳����ݽ����ظ�д
					if (value == null) {
						// ���û�����ݣ���ȥ��DB��ȡ����
						value = "XXX";
					}
				} finally {
					// ��ȡ���ݺ��ͷ�д��
					rwl.writeLock().unlock();
				}
				// ��ȡ�����ݺ󣬻ָ����������¶�ȡ����
				rwl.readLock().lock();
			}
		} finally {
			// ���¶�ȡ�����ͷŶ���
			value = cache.get(key);
			rwl.readLock().unlock();
		}
		return value;
	}
}
