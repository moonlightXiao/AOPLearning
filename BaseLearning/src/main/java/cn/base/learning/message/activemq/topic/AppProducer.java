package cn.base.learning.message.activemq.topic;


import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题模式实例 - 生产者 
 */
public class AppProducer {

	// 默认端口
	private static final String url = "tcp://127.0.0.1:61616";
	private static final String topicName = "topic-test";
	
	
	public static void main(String[] args) throws JMSException {
		// 1、创建连接工厂ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		// 2、创建连接
		Connection connection = connectionFactory.createConnection();
		// 3、启动连接
		connection.start();
		// 4、创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5、创建一个目标,(注意是创建主题模式目标session.createTopic();)
		Destination destination = session.createTopic(topicName);
		// 6、创建生产者
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 100; i++) {
			// 7、创建消息
			TextMessage textMessage = session.createTextMessage("test queue" + i);
			// 8、发布消息
			producer.send(textMessage);
		}
		// 9、关闭连接
		connection.close();
	}

}
