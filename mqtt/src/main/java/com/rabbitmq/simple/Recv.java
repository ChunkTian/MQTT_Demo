package com.rabbitmq.simple;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.util.ConnectionUtil;
/**
 * 消费者
 * @author Administrator
 *
 */
public class Recv {

	private final static String QUEUE_NAME = "test_queue";
	
	public static void main(String[] args) throws Exception {
		
		//获取连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		
		//从连接中创建通道
		Channel channel = connection.createChannel();
		 
		/**
		 *  从队列中获取消息的时候必须要保证队列一定是存在，如果一定存在，则本句可以省略，如果不能，如果
		 *  省略本句，则会报错
		 *  
         * 声明（创建）队列
         * 参数1：队列名称
         * 参数2：为true时server重启队列不会消失(是否持久化 true表示是，队列将在服务器重启时生存)
         * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
         * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
         * 参数5：建立队列时的其他参数
		 */
		//声明(创建)队列  
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
		//定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//监听队列  只要队列中有消息，则立刻能获取
		/**
		 * 参数1：交换机名称
		 * 参数2：队列映射的路由key
		 * 参数3：消息的其他属性
		 * 参数4：发送消息的主体
		 */
		channel.basicConsume(QUEUE_NAME, true,consumer);
		
		//获取消息  设置死循环 一直在获取消息 
		while(true) {
			//如果队列中没有消息   则会进行阻塞，一有消息  
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			
			String message = new String(delivery.getBody());
			
			System.out.println("[X] Received "+ message + "");
		}
		
	}
	
}
