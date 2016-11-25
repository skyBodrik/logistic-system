package logistic.models;

public class Operator extends User {
    public Operator(String name, String email, String password, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.id = 0;
        this.type = User.TYPE_CLIENT;

        this.save();
    }

}