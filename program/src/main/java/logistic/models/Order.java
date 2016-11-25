package logistic.models;

import logistic.mappers.CityMapper;
import logistic.mappers.OrderMapper;

public class Order {
    public static final int STATUS_NONE = 0;
    public static final int STATUS_NEW = 1;
    public static final int STATUS_WAITING_PAYMENT = 2;
    public static final int STATUS_PAID_WAITING_TO_DISPATCH = 3;
    public static final int STATUS_IN_TRANSIT = 4;
    public static final int STATUS_DELIVERED = 5;
    public static final int STATUS_REFUSED = 6;

    public static final String[] statusNames = {
            "-",
            "Новый",
            "Ожидает оплаты",
            "Оплачен. Ожидает отправки.",
            "В пути",
            "Доставлен",
            "Отменён"
    };

    protected int id;
    protected double weight;
    protected double width;
    protected double height;
    protected double length;
    protected int fromCityId;
    protected String fromAddress;
    protected int toCityId;
    protected String toAddress;
    protected String recipientName;
    protected String recipientPhone;
    protected double cost;
    protected int status;
    protected int clientId;
    protected int carrierId;
    protected String dateCreate;

    public Order(
            double weight,
            double width,
            double height,
            double length,
            int fromCityId,
            String fromAddress,
            int toCityId,
            String toAddress,
            String recipientName,
            String recipientPhone,
            int clientId
    ) {
        this.setWeight(weight);
        this.setWidth(width);
        this.setHeight(height);
        this.setLength(length);
        this.setFromCityId(fromCityId);
        this.setFromAddress(fromAddress);
        this.setToCityId(toCityId);
        this.setToAddress(toAddress);
        this.setRecipientName(recipientName);
        this.setRecipientPhone(recipientPhone);
        this.setClientId(clientId);
        this.setStatus(Order.STATUS_NEW);
        this.setCost(0);
        this.setCarrierId(0);
        this.setDateCreate("");
        this.setId(0);

        this.save();
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getFromCityId() {
        return fromCityId;
    }

    public void setFromCityId(int fromCityId) {
        this.fromCityId = fromCityId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public int getToCityId() {
        return toCityId;
    }

    public void setToCityId(int toCityId) {
        this.toCityId = toCityId;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDetails() {
        CityMapper.getById(this.toCityId).getName();
        return "Стоимость: " + (this.getCost() > 0 ? this.getCost() + " руб." : " не доступна")
                + " Адрес отправки: " + CityMapper.getById(this.fromCityId).getName() + ", " + this.getFromAddress()
                + " Адрес доставки: " + CityMapper.getById(this.toCityId).getName() + ", " + this.getToAddress();
    }

    public String getStatusName() {
        return statusNames[this.getStatus()];
    }

    public boolean save() {
        return OrderMapper.save(this);
    }

    public boolean remove() {
        return OrderMapper.remove(this);
    }
}
