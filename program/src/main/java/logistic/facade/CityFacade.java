package logistic.facade;

import logistic.models.City;
import logistic.repositories.CitiesRepository;

import java.util.List;

public class CityFacade {

    public static List<City> getCities() {
        return CitiesRepository.getInstance().getAll();
    }

    public static City getCityById(int id) {
        return CitiesRepository.getInstance().getById(id);
    }
}
