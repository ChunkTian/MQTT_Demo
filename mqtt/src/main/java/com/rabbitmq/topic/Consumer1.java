package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.util.ConnectionUtil;

public class Consumer1 {

	private final static String QUEUE_NAME = "test_queue_topic_1";
	
	private final static String EXCHANGE_NAME = "test_exchange_topic";
	
	public static void main(String[] args) throws Exception {
		
		//获取连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//绑定队列到交换机  
		 /**
         * 向server发布一条消息
         * 参数1：exchange名字，若为空则使用默认的exchange
         * 参数2：routing key
         * 参数3：其他的属性
         * 参数4：消息体
         * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
         * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
         */
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "order.#");
		
		//同一时刻服务器只会发送一条消息给消费者
		channel.basicQos(1);
		
		//定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//监听队列，手动返回完成
		channel.basicConsume(QUEUE_NAME, false,consumer);
		
		while(true){
		    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [财务系统] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
		
	}
}
