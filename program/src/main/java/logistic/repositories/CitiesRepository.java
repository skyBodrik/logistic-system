package logistic.repositories;


import logistic.mappers.CityMapper;
import logistic.models.City;

import java.util.List;

public class CitiesRepository {
    private static CitiesRepository instance;

    public static CitiesRepository getInstance() {
        if (instance == null) {
            instance = new CitiesRepository();
        }

        return instance;
    }

    public List<City> getAll() {
        return CityMapper.getAll();
    }

    public City getById(int id) {
        return CityMapper.getById(id);
    }
}
