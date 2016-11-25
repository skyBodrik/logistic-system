package logistic.repositories;


import logistic.mappers.OrderMapper;
import logistic.models.Order;
import logistic.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersRepository {
    private static OrdersRepository instance;

    public static OrdersRepository getInstance() {
        if (instance == null) {
            instance = new OrdersRepository();
        }

        return instance;
    }

    public List<Order> getAll() {
        return OrderMapper.getAll();
    }

    public List<Order> getAllByClient(User user) {
        return OrderMapper.getAll().stream().filter(order -> order.getClientId() == user.getId()).collect(Collectors.toList());
    }

    public List<Order> getAllByCarrier(User user) {
        return OrderMapper.getAll().stream().filter(order -> order.getCarrierId() == user.getId()).collect(Collectors.toList());
    }
}
