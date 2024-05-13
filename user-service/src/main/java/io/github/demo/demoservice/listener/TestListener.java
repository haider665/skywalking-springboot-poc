package io.github.demo.demoservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mojib.haider
 * @since 5/13/24
 */
@Slf4j
@Component
public class TestListener {

    @RabbitListener(queues = "test-queue")
    public void listener(String message){
        log.info("========== message =============");
        log.info(message);
        log.info("=======================");
    }
}
