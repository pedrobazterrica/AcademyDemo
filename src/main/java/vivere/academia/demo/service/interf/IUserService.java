package vivere.academia.demo.service.interf;

import vivere.academia.demo.dto.LoginRequestDTO;
import vivere.academia.demo.exceptions.UserLoginAlreadyExistsException;
import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(User user) throws UserLoginAlreadyExistsException;
    void updateUser(User user) throws UserNotExistsException;
    void deleteUserById(int userId) throws UserNotExistsException;
    User findUserById(int userId) throws UserNotExistsException;
    List<User> findByQuery(Optional<String> name, Optional<String> email);
    String login(LoginRequestDTO loginRequestDTO) throws UserNotExistsException;
}
