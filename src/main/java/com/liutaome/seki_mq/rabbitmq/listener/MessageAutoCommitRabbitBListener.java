package com.liutaome.seki_mq.rabbitmq.listener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MessageAutoCommitRabbitBListener implements MessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageAutoCommitRabbitBListener.class);

    private AutoCommitRabbitMqListener gomeAutoCommitRabbitMqListener;

	/**  
	 * gomeAutoCommitRabbitMqListener.  
	 *  
	 * @return  the gomeAutoCommitRabbitMqListener  
	 * @since   JDK 1.6  
	 */
	public AutoCommitRabbitMqListener getGomeAutoCommitRabbitMqListener() {
		return gomeAutoCommitRabbitMqListener;
	}

	/**  
	 * gomeAutoCommitRabbitMqListener.  
	 *  
	 * @param   gomeAutoCommitRabbitMqListener    the gomeAutoCommitRabbitMqListener to set  
	 */
	public void setGomeAutoCommitRabbitMqListener(AutoCommitRabbitMqListener gomeAutoCommitRabbitMqListener) {
		this.gomeAutoCommitRabbitMqListener = gomeAutoCommitRabbitMqListener;
	}

	/**  
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see org.springframework.amqp.core.MessageListener#onMessage(org.springframework.amqp.core.Message)  
	 */
	@Override
	public void onMessage(Message arg0) {
		logger.info("MessageAutoCommitRabbitBListener收到消息=="+arg0);
		gomeAutoCommitRabbitMqListener.reciveDoubleMessage(arg0);
	}

	
	
	
	



	

}
