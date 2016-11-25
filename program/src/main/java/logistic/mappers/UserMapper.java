package logistic.mappers;

import logistic.models.Carrier;
import logistic.models.Client;
import logistic.models.Operator;
import logistic.models.User;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper extends MainMapper {
    public static List<User> getAll() {
        String sql = "SELECT `id`, `name`, `email`, `password`, `type`, `phone`, `maxweight`, `width`, `height`, `length` FROM `users` WHERE `type`= :type";
        List<User> list = new ArrayList<User>();
        try (Connection con = sql2o.open()) {
            List<Client> clients;
            clients = con.createQuery(sql)
                    .addParameter("type", User.TYPE_CLIENT)
                    .executeAndFetch(Client.class);
            list.addAll(clients);
            List<Operator> operators;
            operators = con.createQuery(sql)
                    .addParameter("type", User.TYPE_OPERATOR)
                    .executeAndFetch(Operator.class);
            list.addAll(operators);
            List<Carrier> carriers;
            carriers = con.createQuery(sql)
                    .addParameter("type", User.TYPE_CARRIER)
                    .executeAndFetch(Carrier.class);
            list.addAll(carriers);
        }
        return list;
    }

    public static User getById(int id) {
        String sql = "SELECT `id`, `name`, `email`, `password`, `type`, `phone`, `maxweight`, `width`, `height`, `length` FROM `users` WHERE `id`= :id AND `type` = :type";

        try (Connection con = sql2o.open()) {
            Optional<User> clients;
            clients = Optional.ofNullable(con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_CLIENT)
                    .executeAndFetchFirst(Client.class));
            Optional<User> operators;
            operators = Optional.ofNullable(con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_OPERATOR)
                    .executeAndFetchFirst(Operator.class));
            Carrier carriers;
            carriers = con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_CARRIER)
                    .executeAndFetchFirst(Carrier.class);
            return clients.orElse(operators.orElse(carriers));
        }
    }

    public static boolean save(User obj) {
        try (Connection con = sql2o.open()){
            if (obj instanceof Client || obj instanceof Operator) {
                if (obj.getId() == 0) {
                    String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`) "
                            + "VALUES (:type, :name, :email, :password, :phone)";
                    if (con.createQuery(sql)
                            .addParameter("type", User.TYPE_CLIENT)
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
            } else if (obj instanceof Carrier) {
                if (obj.getId() == 0) {
                    String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`, `maxweight`, `width`, `height`, `length`) "
                            + "VALUES (:type, :name, :email, :password, :phone, :maxweight, :width, :height, :length)";
                    if (con.createQuery(sql)
                            .addParameter("type", User.TYPE_CARRIER)
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .addParameter("maxweight", obj.getMaxWeight())
                            .addParameter("width", obj.getWidth())
                            .addParameter("height", obj.getHeight())
                            .addParameter("length", obj.getLength())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                } else {
                    String sql = "UPDATE `users` SET `name` = :name, `email` = :email, `password` = :password, `phone` = :phone, `maxweight` = :maxweight, `width` = :width, `height` = :height, `length` = :length WHERE `id` = :id";
                    if (con.createQuery(sql)
                            .addParameter("id", obj.getId())
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .addParameter("maxweight", obj.getMaxWeight())
                            .addParameter("width", obj.getWidth())
                            .addParameter("height", obj.getHeight())
                            .addParameter("length", obj.getLength())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
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
