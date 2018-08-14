package cn.icmyfuture.iarc.data.receiver;

import cn.icmyfuture.iarc.data.config.RabbitMQConfig;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class DataReceiver {
    @RabbitListener(queues = RabbitMQConfig.DATA_RECEIVER_QUEUE)
    public void process(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
