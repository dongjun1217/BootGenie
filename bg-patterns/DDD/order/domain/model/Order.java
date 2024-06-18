package {BootGenie}.order.domain.model;

import {BootGenie}.order.domain.valueobject.OrderStatus;

public class Order {
    private Long id;
    private Long userId;
    private String product;
    private OrderStatus status;

    public Order(Long id, Long userId, String product, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
