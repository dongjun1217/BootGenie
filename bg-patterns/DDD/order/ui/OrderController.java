package {BootGenie}.order.ui;

import {BootGenie}.order.application.OrderFacade;
import {BootGenie}.order.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @Autowired
    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderFacade.getOrderById(id);
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        orderFacade.createOrder(order);
    }
}
