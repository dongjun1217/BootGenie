package {BootGenie}.order.infrastructure.repository;

import {BootGenie}.order.domain.model.Order;
import {BootGenie}.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private Map<Long, Order> database = new HashMap<>();

    @Override
    public Order findById(Long id) {
        return database.get(id);
    }

    @Override
    public void save(Order order) {
        database.put(order.getId(), order);
    }
}
