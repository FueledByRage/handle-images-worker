package com.microservice.imghandler.cosumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.microservice.imghandler.dtos.HandleImageDTO;
import com.microservice.imghandler.services.Handler;
import com.microservice.imghandler.worker.BackgroundWorker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMqConsumer {

    private final Handler handler;

    private final BackgroundWorker worker;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(HandleImageDTO data) {
        // worker.submitTask(() -> System.out.println("here " + data));
        worker.submitTask(() -> handler.handleImage(data));
    }

}
