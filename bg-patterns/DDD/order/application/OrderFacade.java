package {BootGenie}.order.application;

import {BootGenie}.order.domain.model.Order;
import {BootGenie}.order.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFacade {
    private final OrderService orderService;

    @Autowired
    public OrderFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order getOrderById(Long id) {
        return orderService.getOrderById(id);
    }

    public void createOrder(Order order) {
        orderService.createOrder(order);
    }
}
