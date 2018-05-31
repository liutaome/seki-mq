package com.liutaome.seki_mq.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class MessageRocketBListener implements MessageListenerConcurrently{

	private static final Logger logger = LoggerFactory.getLogger(MessageRocketBListener.class);
	
    private DefaultRocketMqListener gomeDefaultRocketMqListener;

	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeConcurrentlyContext consumeconcurrentlycontext) {
		if(msgs!=null && msgs.size()>0){
			logger.info("MessageRocketBListener收到消息=="+msgs.get(0));
		}
		return gomeDefaultRocketMqListener.reciveDoubleMessage(msgs,consumeconcurrentlycontext);
	}

	/**  
	 * gomeDefaultRocketMqListener.  
	 *  
	 * @param   gomeDefaultRocketMqListener    the gomeDefaultRocketMqListener to set  
	 */
	public void setGomeDefaultRocketMqListener(DefaultRocketMqListener gomeDefaultRocketMqListener) {
		this.gomeDefaultRocketMqListener = gomeDefaultRocketMqListener;
	}

}
