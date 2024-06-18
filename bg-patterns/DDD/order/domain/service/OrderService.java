package {BootGenie}.order.domain.service;

import {BootGenie}.order.domain.model.Order;
import {BootGenie}.order.domain.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
    }
}
