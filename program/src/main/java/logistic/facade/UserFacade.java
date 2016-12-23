package logistic.facade;


import logistic.models.User;
import logistic.repositories.UsersRepository;

public class UserFacade {

    public static User getLoggedUser() {
        return UsersRepository.getInstance().getCurrentUserObject();
    }

    public static void setLoggedUser(User user) {
        UsersRepository repo = UsersRepository.getInstance();
        repo.setCurrentUserObject(user);
    }

    public static User getById(int id) {
        return UsersRepository.getInstance().getById(id);
    }

    public static User login(String email, String password) {
        UsersRepository repo = UsersRepository.getInstance();
        return repo.getByEmailAndPassword(email, password);
    }

    public static User createClient(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createClient(name, email, password, phone);
    }

    public static User createCarrier(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createCarrier(name, email, password, phone);
    }

    public static User createOperator(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createOperator(name, email, password, phone);
    }
}
