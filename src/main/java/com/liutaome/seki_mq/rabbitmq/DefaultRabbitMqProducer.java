/**  
 * Project Name:double-alive  
 * File Name:GomeDefaultRocketMqProducer.java  
 * Package Name:com.gomemyc.rocketmq  
 * Date:2018年4月3日下午5:34:20  
 *  
*/  
/**  
 * @author liutao 
 * Date:2018年4月3日下午5:34:20   
 */  
  
package com.liutaome.seki_mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**  
 * date: 2018年4月3日 下午5:34:20 <br/>  
 * @author liutao  
 */
@Component
@Lazy
public class DefaultRabbitMqProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultRabbitMqProducer.class);
	
	@Value("${doubleFlagRabbit:false}")
	private Boolean doubleFlagRabbit;

	@Autowired
    private AmqpTemplate  amqpTemplateA;
	
	@Autowired
    private AmqpTemplate  amqpTemplateB;
	
	public void sendMessageWithExchange(String exchange,String queue,Object msg){
		try {
			logger.info("flag==="+doubleFlagRabbit);
			logger.info("rabbitmq消息发送通过双活包A==="+msg);
			amqpTemplateA.convertAndSend(exchange,queue,msg);
		} catch (Exception e) {
			logger.info("rabbitmq消息发送失败A"+msg,e);
			if (doubleFlagRabbit) {
				try {
					logger.info("rabbitmq消息发送通过双活包B===" + msg);
					amqpTemplateB.convertAndSend(exchange, queue, msg);
				} catch (Exception e1) {
					logger.info("rabbitmq重试失败B" + msg, e1);
					throw e1;
				} 
			}else{
				throw e;
			}
		}
	}
	
	
	public void sendMessage(Object msg){
		try {
			logger.info("flag==="+doubleFlagRabbit);
			logger.info("rabbitmq-sendMessage消息发送通过双活包A==="+msg);
			amqpTemplateA.convertAndSend(msg);
		} catch (Exception e) {
			logger.info("rabbitmq消息发送失败A"+msg,e);
			if (doubleFlagRabbit) {
				try {
					logger.info("rabbitmq-sendMessage消息发送通过双活包B===" + msg);
					amqpTemplateB.convertAndSend(msg);
				} catch (Exception e1) {
					logger.info("rabbitmq重试失败B" + msg, e1);
					throw e1;
				} 
			}else{
				throw e;
			}
		}
	}

	
}
  
