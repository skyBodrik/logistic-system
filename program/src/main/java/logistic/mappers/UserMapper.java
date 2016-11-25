package logistic.mappers;

import logistic.models.User;
import logistic.models.User;
import org.sql2o.Connection;

import java.util.List;

public class UserMapper extends MainMapper {
    public static List<User> getAll() {
        String sql = "SELECT `id`, `name`, `email`, `password` FROM `users` WHERE `type`= :type";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", User.TYPE_User)
                    .executeAndFetch(User.class);
        }
    }

    public static User getById(int id) {
        String sql = "SELECT `id`, `name`, `email`, `password` FROM `users` WHERE `id`= :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    public static boolean save(User obj) {
        try (Connection con = sql2o.open()){
            if (obj.getId() == 0) {
                String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`) "
                        + "VALUES (:type, :name, :email, :password, :phone)";
                if (con.createQuery(sql)
                        .addParameter("type", User.TYPE_User)
                        .addParameter("name", obj.getName())
                        .addParameter("email", obj.getEmail())
                        .addParameter("password", obj.getPassword())
                        .addParameter("phone", obj.getPhone())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            } else {
                String sql = "UPDATE `users` SET `name` = :name, `email` = :email, `password` = :password, `phone` = :phone WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("id", obj.getId())
                        .addParameter("name", obj.getName())
                        .addParameter("email", obj.getEmail())
                        .addParameter("password", obj.getPassword())
                        .addParameter("phone", obj.getPhone())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean update(User obj) {
        return false;
    }

    public static boolean remove(User obj) {
        try (Connection con = sql2o.open()){
            if (obj.getId() > 0) {
                String sql = "DELETE FROM `users` WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("id", obj.getId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
