package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getAllUsers();

    void update(User user);

    void removeUser(long id);

    User getUser(long id);
}