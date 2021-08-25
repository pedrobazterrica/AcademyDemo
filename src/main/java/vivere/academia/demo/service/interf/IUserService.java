package vivere.academia.demo.service.interf;

import vivere.academia.demo.models.User;

import java.util.HashSet;
import java.util.List;

public interface IUserService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(int userId);
    User findUserById(int userId);
    List<User> findAllUsers();
}
