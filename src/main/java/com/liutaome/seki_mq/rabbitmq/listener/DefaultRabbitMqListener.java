/**  
 * Project Name:double-alive  
 * File Name:GomeDefaultRocketMqConsumer.java  
 * Package Name:com.gomemyc.rocketmq  
 * Date:2018年4月2日下午6:11:46  
 *  
*/  
/**  
 * @author liutao 
 * Date:2018年4月2日下午6:11:46   
 */  
  
package com.liutaome.seki_mq.rabbitmq.listener;


import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;


/**  
 * date: 2018年4月2日 下午6:11:46 <br/>  
 * @author liutao  test
 */
public interface DefaultRabbitMqListener{
    
    void reciveDoubleMessage(Message message, Channel channel);
    
}
  
