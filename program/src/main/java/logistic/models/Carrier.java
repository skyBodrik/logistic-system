package logistic.models;

public class Carrier extends User {
    public Carrier(String name, String email, String password, String phone, double maxweight, double width, double height, double length) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.setMaxWeight(maxweight);
        this.setWidth(width);
        this.setHeight(height);
        this.setLength(length);
        this.id = 0;
        this.type = User.TYPE_CARRIER;

        this.save();
    }

}