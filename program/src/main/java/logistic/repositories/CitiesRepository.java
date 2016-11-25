package logistic.repositories;


import logistic.mappers.OrderMapper;
import logistic.models.Order;
import logistic.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class CitiesRepository {
    private static CitiesRepository instance;

    public static CitiesRepository getInstance() {
        if (instance == null) {
            instance = new CitiesRepository();
        }

        return instance;
    }

    public List<Order> getAll() {
        return OrderMapper.getAll();
    }

    public List<Order> getAllByUser(User user) {
        return OrderMapper.getAll().stream().filter(order -> order.getClientId() == user.getId()).collect(Collectors.toList());
    }

    public List<Order> getAllByCarrier(User user) {
        return OrderMapper.getAll().stream().filter(order -> order.getCarrierId() == user.getId()).collect(Collectors.toList());
    }
}
