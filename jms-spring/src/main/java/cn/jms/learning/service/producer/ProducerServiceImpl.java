package cn.jms.learning.service.producer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.text.TextAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProducerServiceImpl implements ProducerService {

	// 使用配置好的JmsTemplate
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Resource(name = "queueDestination")
	Destination destination;
	
	public void sendMessage(final String message) {
		// 使用JmsTemplate发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
	}

}
