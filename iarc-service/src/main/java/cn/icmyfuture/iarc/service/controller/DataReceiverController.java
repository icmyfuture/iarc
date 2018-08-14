package cn.icmyfuture.iarc.service.controller;

import cn.icmyfuture.iarc.entity.User;
import cn.icmyfuture.iarc.service.config.RabbitMQConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RestController
@RequestMapping(value = "/data_receiver")
@Api(tags = "data_receiver", description = "数据接收接口")
public class DataReceiverController implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @ApiOperation(value="接受消息", notes="接受消息")
    @CrossOrigin(origins = "*")
    @PostMapping("receive")
    public void receive(@RequestBody User user) {
        sendToMQ(user.getName());
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
    }

    private void sendToMQ(String msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.DATA_RECEIVER_EXCHANGE,
                RabbitMQConfig.DATA_RECEIVER_KEY, msg, correlationId);
    }
}
