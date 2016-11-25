package logistic.mappers;

import logistic.models.Calendar;
import org.sql2o.Connection;

import java.util.List;

public class CalendarMapper extends MainMapper {

    public static List<Calendar> getAll() {
        String sql = "SELECT `id`, `date`, `user_id` AS `userId`, `city_id` AS `cityId` FROM `calendar`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Calendar.class);
        }
    }

    public static Calendar getById(int id) {
        String sql = "SELECT `id`, `date`, `user_id` AS `userId`, `city_id` AS `cityId` FROM `calendar` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Calendar.class);
        }
    }

    public static boolean save(Calendar obj) {
        if (obj.getUserId() == 0 || obj.getCityId() == 0) {
            return false;
        }
        try (Connection con = sql2o.open()) {
            if (obj.getId() == 0) {
                String sql = "INSERT INTO `calendar` (`date`, `user_id`, `city_id`) "
                        + "VALUES (:date, :user_id, :city_id)";
                if (con.createQuery(sql)
                        .addParameter("date", obj.getDate())
                        .addParameter("user_id", obj.getUserId())
                        .addParameter("city_id", obj.getCityId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            } else {
                String sql = "UPDATE `calendar` SET `date` = :date, `user_id` = :user_id, `city_id` = :city_id WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("id", obj.getId())
                        .addParameter("date", obj.getDate())
                        .addParameter("user_id", obj.getUserId())
                        .addParameter("city_id", obj.getCityId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean remove(Calendar obj) {
        return false;
    }
}
