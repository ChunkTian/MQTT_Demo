订阅模式：
	1、一个生产者，多个消费者
	2、每一个消费者都有自己的一个队列
	3、生产者没有将消息直接发送到队列，而是发送到了交换机
	4、每个队列都要绑定到交换机
	5、生产者发送的消息，经过交换机，到达队列，实现一个消息被多个消费者获取的目的
	
注意：消息发送到没有队列绑定的交换机时，消息就将丢失，因为，交换机没有存储消息的能力，消息只能存在队列中

Exchange类型：
	Direct、Fanout、Topic三中类型，RabbitMQ默认有一个exchange，叫default exchange 他用一个
	空字符串表示，他是direct exchange类型



