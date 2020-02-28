package com.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	public static Connection getConnection()throws Exception{
		//定义连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//配置服务地址
		factory.setHost("123.57.239.84");
		//端口
		factory.setPort(5672);
		//设置账号信息，用户名 密码 vhost
		factory.setVirtualHost("/");
		factory.setUsername("guest");
		factory.setPassword("newnewnew");
		//通过工程获取连接
		Connection connection = factory.newConnection();
		return connection;
	}
}
