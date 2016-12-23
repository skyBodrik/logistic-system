package logistic.mappers;

import logistic.models.Carrier;
import logistic.models.Client;
import logistic.models.Operator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class UserMapperTest extends Assert {
    @Test
    public void testGetById() {
        assertTrue(UserMapper.getById(1) instanceof Operator);
        assertTrue(UserMapper.getById(2) instanceof Client);
        assertTrue(UserMapper.getById(3) instanceof Carrier);

        assertNull(UserMapper.getById(0));
    }
}
