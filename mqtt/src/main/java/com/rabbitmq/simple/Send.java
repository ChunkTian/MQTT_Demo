package com.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;

/**
 * 发送者
 * @author Administrator
 *
 */
public class Send {
	
	private final static String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws Exception {
		//获取连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		//从连接中创建通道
		Channel channel = connection.createChannel();
		 
		//声明(创建)队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//消息内容
		String message = "Hello World---1";
		 /**
         * 向server发布一条消息
         * 参数1：exchange名字，若为空则使用默认的exchange
         * 参数2：routing key
         * 参数3：其他的属性
         * 参数4：消息体
         * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
         * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
         */
		//消息发送
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//		System.out.println("[x] Sent "+message);
		//关闭通道和连接
		channel.close();
		connection.close();
		
	}
}
