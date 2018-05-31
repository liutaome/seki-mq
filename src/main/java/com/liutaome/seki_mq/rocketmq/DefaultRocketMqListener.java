package com.liutaome.seki_mq.rocketmq;

 
/**  
 * @author liutao 
 * Date:2018年4月2日下午6:11:46   
 */  
  

import java.util.List;


import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;

/**  
 * date: 2018年4月2日 下午6:11:46 <br/>  
 * @author liutao  
 */
public interface DefaultRocketMqListener{
    
    ConsumeConcurrentlyStatus reciveDoubleMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context);
    
}
  
