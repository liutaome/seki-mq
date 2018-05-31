package com.liutaome.seki_mq.rocketmq;


/**  
 * @author liutao 
 * Date:2018年4月3日下午5:34:20   
 */  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**  
 * date: 2018年4月3日 下午5:34:20 <br/>  
 * @author liutao  
 */
@Component
@Lazy
public class DefaultRocketMqProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultRocketMqProducer.class);

	@Autowired
	private DefaultMQProducer producerA;
	
	@Autowired
	private DefaultMQProducer producerB;
	
	@Value("${doubleFlagRocket:false}")
	private Boolean doubleFlagRocket;
	
	public SendResult sendMessage(Message msg){
		logger.info("flag==="+doubleFlagRocket);
		SendResult result = null;
				
		try {
			logger.info("rocketMqA发送消息双活包=="+msg);
			result = producerA.send(msg);
		} catch (MQClientException e) {
			logger.error("MQClientException",e);
		} catch (RemotingException e) {
			logger.error("RemotingException",e);
		} catch (MQBrokerException e) {
			logger.error("MQBrokerException",e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException",e);
		}
		logger.info(msg+"返回结果A为=="+result);
		
		if(result!=null && result.getSendStatus()==SendStatus.SEND_OK){
			return result;
		}
		
		if(!doubleFlagRocket){
			return result;
		}
		
		try {
			logger.info("rocketMqB发送消息双活包=="+msg);
			result = producerB.send(msg);
		} catch (MQClientException e) {
			logger.error("MQClientException",e);
		} catch (RemotingException e) {
			logger.error("RemotingException",e);
		} catch (MQBrokerException e) {
			logger.error("MQBrokerException",e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException",e);
		}
		logger.info(msg+"返回结果B为=="+result);
		return result;
	}

	/**  
	 * producerA.  
	 *  
	 * @param   producerA    the producerA to set  
	 */
	public void setProducerA(DefaultMQProducer producerA) {
		this.producerA = producerA;
	}

	/**  
	 * producerB.  
	 *  
	 * @param   producerB    the producerB to set  
	 */
	public void setProducerB(DefaultMQProducer producerB) {
		this.producerB = producerB;
	}
	
}
  
