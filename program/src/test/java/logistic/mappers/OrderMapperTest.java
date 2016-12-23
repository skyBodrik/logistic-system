package logistic.mappers;

import logistic.models.Order;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class OrderMapperTest extends Assert {
    @Test
    public void testGetById() {
        assertTrue(OrderMapper.getById(1) instanceof Order);

        assertTrue(OrderMapper.getById(1).getClientId() > 0);

        assertNull(OrderMapper.getById(0));
    }
}
