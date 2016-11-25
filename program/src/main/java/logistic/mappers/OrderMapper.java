package logistic.mappers;

import logistic.models.Order;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapper extends MainMapper {

    public OrderMapper() {
        Map<String, String> colMaps = new HashMap<>();
        colMaps.put("from_city_id", "fromCityId");
        colMaps.put("from_address", "fromAddress");
        colMaps.put("to_city_id", "toCityId");
        colMaps.put("to_address", "toAddress");
        colMaps.put("recipient_name", "recipientName");
        colMaps.put("recipient_phone", "recipientPhone");
        colMaps.put("client_id", "clientId");
        colMaps.put("carrier_id", "carrierId");
        colMaps.put("date_create", "dateCreate");

        sql2o.setDefaultColumnMappings(colMaps);
    }

    public static List<Order> getAll() {
        String sql = "SELECT `id`, `weight`, `width`, `height`, `length`, `from_city_id` AS 'fromCityId', `from_address` AS 'fromAddress', `to_city_id` AS 'toCityId', `to_address` AS 'toAddress', `recipient_name` AS 'recipientName', `recipient_phone` AS 'recipientPhone', `cost`, `status`, `client_id` AS 'clientId', `carrier_id` AS 'carrierId', `date_create` AS 'dateCreate' FROM `orders`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Order.class);
        }
    }

    public static Order getById(int id) {
        String sql = "SELECT `id`, `weight`, `width`, `height`, `length`, `from_city_id` AS 'fromCityId', `from_address` AS 'fromAddress', `to_city_id` AS 'toCityId', `to_address` AS 'toAddress', `recipient_name` AS 'recipientName', `recipient_phone` AS 'recipientPhone', `cost`, `status`, `client_id` AS 'clientId', `carrier_id` AS 'carrierId', `date_create` AS 'dateCreate' FROM `orders` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Order.class);
        }
    }

    public static boolean save(Order obj) {
        try (Connection con = sql2o.open()) {
            if (obj.getId() == 0) {
                String sql = "INSERT INTO `orders` (`weight`, `width`, `height`, `length`, `from_city_id`, `from_address`, `to_city_id`, `to_address`, `recipient_name`, `recipient_phone`, `cost`, `status`, `client_id`, `carrier_id`) "
                        + "VALUES (:weight, :width, :height, :length, :from_city_id, :from_address, :to_city_id, :to_address, :recipient_name, :recipient_phone, :cost, :status, :client_id, :carrier_id)";
                if (con.createQuery(sql)
                        .addParameter("weight", obj.getWeight())
                        .addParameter("width", obj.getWidth())
                        .addParameter("height", obj.getHeight())
                        .addParameter("length", obj.getLength())
                        .addParameter("from_city_id", obj.getFromCityId())
                        .addParameter("from_address", obj.getFromAddress())
                        .addParameter("to_city_id", obj.getToCityId())
                        .addParameter("to_address", obj.getToAddress())
                        .addParameter("recipient_name", obj.getRecipientName())
                        .addParameter("recipient_phone", obj.getRecipientPhone())
                        .addParameter("cost", obj.getCost())
                        .addParameter("status", obj.getStatus())
                        .addParameter("client_id", obj.getClientId())
                        .addParameter("carrier_id", obj.getCarrierId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            } else {
                String sql = "UPDATE `orders` SET `weight` = :weight, `width` = :width, `height` = :height, `length` = :length, `from_city_id` = :from_city_id, `from_address` = :from_address, `to_city_id` = :to_city_id, `to_address` = :to_address, `recipient_name` = :recipient_name, `recipient_phone` = :recipient_phone, `cost` = :cost, `status` = :status, `client_id` = :client_id, `carrier_id` = :carrier_id WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("weight", obj.getWeight())
                        .addParameter("width", obj.getWidth())
                        .addParameter("height", obj.getHeight())
                        .addParameter("length", obj.getLength())
                        .addParameter("from_city_id", obj.getFromCityId())
                        .addParameter("from_address", obj.getFromAddress())
                        .addParameter("to_city_id", obj.getToCityId())
                        .addParameter("to_address", obj.getToAddress())
                        .addParameter("recipient_name", obj.getRecipientName())
                        .addParameter("recipient_phone", obj.getRecipientPhone())
                        .addParameter("cost", obj.getCost())
                        .addParameter("status", obj.getStatus())
                        .addParameter("client_id", obj.getClientId())
                        .addParameter("carrier_id", obj.getCarrierId())
                        .addParameter("id", obj.getId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean remove(Order obj) {
        try (Connection con = sql2o.open()){
            if (obj.getId() > 0) {
                String sql = "DELETE FROM `orders` WHERE `id` = :id";
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
