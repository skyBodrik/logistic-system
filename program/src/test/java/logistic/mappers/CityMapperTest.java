package logistic.mappers;

import logistic.models.City;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class CityMapperTest extends Assert {
    @Test
    public void testGetById() {
        assertTrue(CityMapper.getById(1) instanceof City);

        assertNull(CityMapper.getById(0));
    }
}
