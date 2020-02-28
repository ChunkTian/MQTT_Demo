RabbitMQ相关术语：
	1、Broker：消息队列服务器实体
	2、Exchange:消息交换机，他指定消息按什么规则，路由到哪个队列
	3、Queue：消息队列载体，每个消息都会被投入到一个或多个队列
	4、Binding：绑定，他的作用是把exchange和queue按照路由规则绑定起来
	5、Routing Key:路由关键字，exchange根据这个关键字进行消息投递
	6、vhost：虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离
	7、producer：消息生产者，就是投递消息的程序
	8、consumer：消息消费者
	9、channel：消息通道，在客户端的每个链接里，可建立多个channel，每个channel代表一个会话任务
	
	
	
RabbitMQ常用发布订阅模式的运行流程：
	AMQP模型中，消息在producer中产生，发送到MQ的exchange上，exchange根据配置的路由方式发到相应的Queue上，
	Queue又将消息发送给consumer，消息从queue到consumer有push和pull两种方式。 消息队列的使用过程大概如下：
		  1.客户端连接到消息队列服务器，打开一个channel。
          2.客户端声明一个exchange，并设置相关属性。
          3.客户端声明一个queue，并设置相关属性。
          4.客户端使用routing key，在exchange和queue之间建立好绑定关系。
          5.客户端投递消息到exchange。