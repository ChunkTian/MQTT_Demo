package com.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;

public class Producer {

	private final static String QUEUE_NAME = "computer";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		for(int i=0;i<30;i++){
			
			String message = ""+i;
			
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			
			//发送消息的时间越来越长
			Thread.sleep(i*10);
			
		}
		
		channel.close();
		
		connection.close();
		
	}
	
	
	
}
