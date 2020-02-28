package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;

/**
 * 通配模式
 * @author Administrator
 *
 */
public class Producer {


	private final static String EXCHANGE_NAME = "test_exchange_topic";
	
	
	public static void main(String[] args) throws Exception {
		 // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
		
        //声明exchange  交换机名称  交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        
        //消息内容 模拟 有人购物下单
        String message = "新增订单:id=101";
        
        //发布 
        /**
         * 向server发布一条消息
         * 参数1：exchange名字，若为空则使用默认的exchange
         * 参数2：routing key
         * 参数3：其他的属性
         * 参数4：消息体
         * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
         * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
         */
        channel.basicPublish(EXCHANGE_NAME, "order.insert", null, message.getBytes());
		
        System.err.println("生产者 Sent "+ message);
		
        channel.close();
        
        connection.close();
		
	}
	
}
