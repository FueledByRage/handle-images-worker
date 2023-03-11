package com.microservice.imghandler.cosumers;

import com.microservice.imghandler.utils.DtoUtils;
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
        try {
            if (DtoUtils.hasNullFields(data))
                throw new IllegalArgumentException("Data fields can not be empty.");
            worker.submitTask(() -> handler.handleImage(data));

        } catch (IllegalAccessException | IllegalArgumentException error) {
            System.out.println("Error trying to validate data: " + error);
        }
    }

}
