package com.rabbitmq.work;
 
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.util.ConnectionUtil;
 
public class Consumer2 {
 
	private final static String QUEUE_NAME = "computer";
 
    public static void main(String[] argv) throws Exception {
 
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
 
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
 
        // 同一时刻服务器只会发一条消息给消费者(能者多劳模式) 当不注释 时， 两个消费者获取的消息数量相同
        channel.basicQos(1);
 
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成状态 false为手动     true为自动
        channel.basicConsume(QUEUE_NAME, false, consumer);
 
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [消费者2] Received '" + message + "'");
            // 休眠1秒
            Thread.sleep(1000);
            //反馈消息的消费状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
