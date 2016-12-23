package logistic.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bodrik on 23.12.16.
 */
public class CalendarTest extends Assert {

    @Test
    public void testGetDetails() {
        Calendar c = new Calendar("2016-12-20", 1, 1);
        assertEquals("Пункт пребывания: Красноярск; Перевозчик: Операторов Оператор", c.getDetails());

        c = new Calendar("2016-12-20", 1, 2);
        assertEquals("Пункт пребывания: Санкт-Петербург; Перевозчик: Операторов Оператор", c.getDetails());

        c = new Calendar("-", 1, 0);
        assertEquals("-", c.getDetails());
    }

    @Test
    public void testGetCityName() {
        Calendar c = new Calendar("2016-12-20", 1, 1);
        assertEquals("Красноярск", c.getCityName());

        c = new Calendar("2016-12-20", 1, 2);
        assertEquals("Санкт-Петербург", c.getCityName());

        c = new Calendar("2016-12-20", 1, 0);
        assertEquals("-", c.getCityName());
    }

}
