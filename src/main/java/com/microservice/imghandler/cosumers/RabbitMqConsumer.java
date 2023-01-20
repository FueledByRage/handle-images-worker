package com.microservice.imghandler.cosumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.imghandler.dtos.HandleImageDTO;
import com.microservice.imghandler.services.Handler;
import com.microservice.imghandler.worker.BackgroundWorker;

@Component
public class RabbitMqConsumer {
    
    @Autowired
    Handler handler;

    @Autowired
    BackgroundWorker worker;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(HandleImageDTO data ){
        worker.submitTask( () -> handler.handleImage(data));
    }

}
