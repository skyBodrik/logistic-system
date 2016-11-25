package logistic.repositories;

import logistic.mappers.UserMapper;
import logistic.models.Carrier;
import logistic.models.Client;
import logistic.models.Operator;
import logistic.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.security.MessageDigest;

/**
 * Created by bodrik on 23.11.16.
 */
public class UsersRepository {
    private static UsersRepository instance;
    private User currentUserObject;

    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }

        return instance;
    }

    private static String md5Convert(String str) {
        String outStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            outStr = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.print(e.getMessage());
        }
        return outStr;
    }

    public List<User> getAll(int type) {
        List<User> list;
        if (type == 0) {
            list = UserMapper.getAll();
        } else {
            list = UserMapper.getAll().stream().filter(p -> p.getType() == type).collect(Collectors.toList());
        }
        return list;
    }

    public User getById(int id) {
        return UserMapper.getById(id);
    }

    public User getByEmailAndPassword(String email, String password) {
        String passwordHash = md5Convert(password);
        return UserMapper.getAll().stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(passwordHash)).findFirst().orElse(null);
    }

    public void setCurrentUserObject(User user) {
        this.currentUserObject = user;
    }

    public User getCurrentUserObject() {
        return this.currentUserObject;
    }
}
