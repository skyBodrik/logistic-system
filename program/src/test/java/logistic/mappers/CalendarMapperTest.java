package logistic.mappers;

import logistic.models.Calendar;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class CalendarMapperTest extends Assert {
    @Test
    public void testGetById() {
        assertTrue(CalendarMapper.getById(1) instanceof Calendar);

        assertNull(CalendarMapper.getById(0));
    }
}
