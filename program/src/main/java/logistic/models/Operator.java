package logistic.models;

public class Operator extends User {
    public Operator(String name, String email, String password, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.maxweight = 0;
        this.width = 0;
        this.height = 0;
        this.length = 0;
        this.id = 0;
        this.type = User.TYPE_OPERATOR;

        this.save();
    }

}