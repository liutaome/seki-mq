package com.liutaome.seki_mq.rabbitmq.listener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;


@Component
@Lazy
public class MessageRabbitAListener implements ChannelAwareMessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRabbitAListener.class);

    private DefaultRabbitMqListener gomeDefaultRabbitMqListener;

	/**  
	 * gomeDefaultRabbitMqListener.  
	 *  
	 * @param   gomeDefaultRabbitMqListener    the gomeDefaultRabbitMqListener to set  
	 */
	public void setGomeDefaultRabbitMqListener(DefaultRabbitMqListener gomeDefaultRabbitMqListener) {
		this.gomeDefaultRabbitMqListener = gomeDefaultRabbitMqListener;
	}

	/**  
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see org.springframework.amqp.rabbit.core.ChannelAwareMessageListener#onMessage(org.springframework.amqp.core.Message, com.rabbitmq.client.Channel)  
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		logger.info("MessageRabbitAListener收到消息"+message);
		gomeDefaultRabbitMqListener.reciveDoubleMessage(message,channel); 
	}
	
	
	



	

}
