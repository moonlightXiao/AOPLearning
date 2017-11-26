package cn.base.learning.message.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题模式实例 - 消费者
 */
public class AppConsumer {

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
		// 5、创建一个目标(注意是创建队列模式目标session.createQueue())
		Destination destination = session.createTopic(topicName);
		// 6、创建一个消费者
		MessageConsumer consumer = session.createConsumer(destination);
		// 7、创建一个监听器(注意消息的监听是个异步的过程)
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("主题模式消费消息 ： " + textMessage.getText());
				} catch (JMSException e) {
				}
			}
		});
		// 8、关闭连接(注意消息的监听是个异步的过程，所以在此不能先关闭连接)
//		connection.close();
	}

}
