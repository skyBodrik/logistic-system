package logistic.models;


import logistic.mappers.CalendarMapper;
import logistic.repositories.CitiesRepository;
import logistic.repositories.UsersRepository;

public class Calendar {
    protected int id;
    protected String date;
    protected int userId;
    protected int cityId;

    public Calendar(String date, int userId, int cityId) {
        this.date = date;
        this.userId = userId;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        if (this.getCityId() == 0) {
            return "-";
        }
        return CitiesRepository.getInstance().getById(this.getCityId()).getName();
    }

    public String getDetails() {
        if (this.getCityId() == 0) {
            return "-";
        }
        return "Пункт пребывания: " + CitiesRepository.getInstance().getById(this.getCityId()).getName()
                + "; Перевозчик: " + UsersRepository.getInstance().getById(this.getUserId()).getName();
    }

    public boolean save() {
        return CalendarMapper.save(this);
    }

    public boolean remove() {
        return CalendarMapper.remove(this);
    }

}
