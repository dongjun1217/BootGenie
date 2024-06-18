package {BootGenie}.order.domain.repository;

import {BootGenie}.order.domain.model.Order;

public interface OrderRepository {
    Order findById(Long id);
    void save(Order order);
}
