package com.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.util.ConnectionUtil;

public class Consumer2 {


	private final static String QUEUE_NAME = "test_queue_direct_2";
	
	private final static String EXCHANGE_NAME = "test_exchange_direct";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		
		Channel channel = connection.createChannel();
		
		//声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "B");
        
        //如果想让消费之2 同时接受routingKey 为A 和B的消息，只要在下面在此添加一个Bing就可以了
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "A");
        
        channel.basicQos(1);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		channel.basicConsume(QUEUE_NAME, false,consumer);
		
		//获取消息
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [消费者2] Received '" + message + "'");
            Thread.sleep(10);
 
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
	
	
}
