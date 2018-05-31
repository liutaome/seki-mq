/**  
 * Project Name:gome-finance-provider-sale  
 * File Name:ConnectionCLoseListener.java  
 * Package Name:com.gomemyc.service  
 * Date:2018年4月26日下午4:06:04  
 *  
*/  
/**  
 * @author liutao 
 * Date:2018年4月26日下午4:06:04   
 */  
  
package com.liutaome.seki_mq.rabbitmq.listener;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import com.liutaome.seki_mq.util.SpringContextInnerUtil;



/**  
 * date: 2018年4月26日 下午4:06:04 <br/>  
 * @author liutao  
 */
public class ConnectionFactoryListener{
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionFactoryListener.class);
	
	/**  
	 * Creates a new instance of ConnectionCLoseListener.    
	 */
	@PostConstruct
	public void addListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("添加监听器准备");
				try {
					Thread.sleep(300000);
				} catch (InterruptedException e) {
					logger.error("添加监听器等待异常");
				}
				logger.info("添加监听器进行中");
				addAListener();
				addBListener();
				logger.info("添加监听器结束");
			}
		}).start();
	}
	
	
	private void addAListener(){
		CachingConnectionFactory facA = SpringContextInnerUtil.getBean("amqpConnectionFactoryA");
		List<ConnectionListener> listA = new ArrayList<ConnectionListener>();
		listA.add(new ConnectionAListener());
		facA.clearConnectionListeners();
		facA.setConnectionListeners(listA);
	}
	
	private void addBListener(){
		CachingConnectionFactory facB = SpringContextInnerUtil.getBean("amqpConnectionFactoryB");
		List<ConnectionListener> listB = new ArrayList<ConnectionListener>();
		listB.add(new ConnectionBListener());
		facB.clearConnectionListeners();
		facB.setConnectionListeners(listB);
	}
	
	
	private void recoverA(Connection connection){
		int i=0;
		while(i<2){
			logger.info("A尝试重新启动");
			try {
				Thread.sleep(3000);
				RabbitAdmin admin = SpringContextInnerUtil.getBean("adminA");
                admin.initialize();
			} catch (Exception e) {
				logger.info("A重启异常");
				continue;
			}
			i++;
		}
	}
	
	
	private void recoverB(Connection connection){
		int i=0;
		while(i<2){
			logger.info("B尝试重新启动");
			try {
				Thread.sleep(3000);
				RabbitAdmin admin = SpringContextInnerUtil.getBean("adminB");
				admin.initialize();
			} catch (Exception e) {
				logger.info("B重启异常");
				continue;
			}
			i++;
		}
	}
	
	
	class ConnectionAListener implements ConnectionListener{

		/**  
		 * TODO 简单描述该方法的实现功能（可选）.  
		 * @see org.springframework.amqp.rabbit.connection.ConnectionListener#onClose(org.springframework.amqp.rabbit.connection.Connection)  
		 */
		@Override
		public void onClose(Connection connection) {
			logger.info("A连接正在关闭==="+connection);
			recoverA(connection);
		}

		/**  
		 * TODO 简单描述该方法的实现功能（可选）.  
		 * @see org.springframework.amqp.rabbit.connection.ConnectionListener#onCreate(org.springframework.amqp.rabbit.connection.Connection)  
		 */
		@Override
		public void onCreate(Connection connection) {
			logger.info("A正在创建连接==="+connection);
		}
		
	}
	
	
	class ConnectionBListener implements ConnectionListener{

		/**  
		 * TODO 简单描述该方法的实现功能（可选）.  
		 * @see org.springframework.amqp.rabbit.connection.ConnectionListener#onClose(org.springframework.amqp.rabbit.connection.Connection)  
		 */
		@Override
		public void onClose(Connection connection) {
			logger.info("B连接正在关闭==="+connection);
			recoverB(connection);
		}

		/**  
		 * TODO 简单描述该方法的实现功能（可选）.  
		 * @see org.springframework.amqp.rabbit.connection.ConnectionListener#onCreate(org.springframework.amqp.rabbit.connection.Connection)  
		 */
		@Override
		public void onCreate(Connection connection) {
			logger.info("B正在创建连接==="+connection);
		}
		
	}
	
	
}
  
