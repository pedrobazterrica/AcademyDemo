package vivere.academia.demo.service.interf;

import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.models.User;

import java.util.HashSet;
import java.util.List;

public interface IUserService {
    void createUser(User user);
    void updateUser(User user) throws UserNotExistsException;
    void deleteUserById(int userId) throws UserNotExistsException;
    User findUserById(int userId) throws UserNotExistsException;
    List<User> findAllUsers();
}
