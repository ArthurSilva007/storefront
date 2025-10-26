package br.com.dio.storefront.service.impl;

import br.com.dio.storefront.dto.StockStatusMessage;
import br.com.dio.storefront.service.IProductChangeAvailabilityConsumer;
import br.com.dio.storefront.service.IProductservice;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductChangeAvailabiltyConsumeImpl implements IProductChangeAvailabilityConsumer {

    private final IProductservice service;

    @RabbitListener(queues = "${spring.rabbitmq.queue.product-change-availability}")
    @Override
    public void receive(final StockStatusMessage message) {
        service.changeActivated(message.Id(), message.active());
    }

}
