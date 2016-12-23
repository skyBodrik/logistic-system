package logistic.models;

import logistic.repositories.OrdersRepository;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class OrderTest extends Assert {

    @Test
    public void testGetDetails() {
        Order order = OrdersRepository.getInstance().getAll().stream().filter(p -> p.getId() == 1).findFirst().get();
        assertEquals("Стоимость: 1500.0 руб. Адрес отправки: Красноярск, ул. Робеспьера Адрес доставки: Санкт-Петербург, пр-кт Энгельса", order.getDetails());

        assertTrue(OrdersRepository.getInstance().getAll().stream().filter(p -> p.getId() == 0).count() == 0);
    }

    @Test
    public void testGetStatusJson() {
        Order order = OrdersRepository.getInstance().getAll().stream().filter(p -> p.getId() == 2).findFirst().get();
        assertEquals("{\"id\":2,\"statusCode\":4,\"statusText\":\"В пути\"}", order.getStatusOrderJson());
    }

}
