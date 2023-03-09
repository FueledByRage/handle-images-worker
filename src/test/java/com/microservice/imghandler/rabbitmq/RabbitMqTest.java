package com.microservice.imghandler.rabbitmq;

//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.imghandler.configs.RabbitMqConfig;
import com.microservice.imghandler.cosumers.RabbitMqConsumer;
import com.microservice.imghandler.dtos.HandleImageDTO;
import com.microservice.imghandler.worker.BackgroundWorker;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RabbitMqTest {

    @Mock
    private BackgroundWorker backgroundWorker;

    @InjectMocks
    private RabbitMqConsumer consumer;

    @Autowired
    RabbitMqConfig rabbitMq;

    RabbitTemplate rabbitHole;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @BeforeEach
    void setup() {
        rabbitHole = rabbitMq.rabbitTemplate();
        rabbitHole.setExchange("");
        rabbitHole.setRoutingKey(queue);
    }

    void emitMessage(HandleImageDTO messageData) {
        rabbitHole.convertAndSend(queue, messageData);
    }

    @Test
    void consumeMessageTest() throws Exception {
        HandleImageDTO sentMessage = new HandleImageDTO();
        sentMessage.setBucketName("Just testing");
        consumer.listen(sentMessage);
        verify(backgroundWorker).submitTask(any(Runnable.class));
    }
}
