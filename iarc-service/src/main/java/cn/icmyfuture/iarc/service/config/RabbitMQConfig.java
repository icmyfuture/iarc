package cn.icmyfuture.iarc.service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig implements InitializingBean {
    public static final String DATA_RECEIVER_EXCHANGE = "data-receiver-exchange";
    public static final String DATA_RECEIVER_QUEUE = "data-receiver-queue";
    public static final String DATA_RECEIVER_KEY = "data-receiver-key";

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    //为了开启setAutomaticRecoveryEnabled功能，连接中断后自动恢复
    @Override
    public void afterPropertiesSet() {
        com.rabbitmq.client.ConnectionFactory conn = cachingConnectionFactory.getRabbitConnectionFactory();
        conn.setAutomaticRecoveryEnabled(true);
        conn.setTopologyRecoveryEnabled(true);
    }

    @Bean
    public Queue dataReceiverQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        return new Queue(DATA_RECEIVER_QUEUE, true, false, false, args);
    }

    @Bean
    TopicExchange dataReceiverExchange() {
        return new TopicExchange(DATA_RECEIVER_EXCHANGE);
    }

    @Bean
    Binding bindingDataReceive() {
        return BindingBuilder.bind(dataReceiverQueue()).to(dataReceiverExchange()).with(DATA_RECEIVER_KEY);
    }
}
