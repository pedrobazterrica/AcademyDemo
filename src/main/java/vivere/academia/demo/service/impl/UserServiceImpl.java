package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.models.User;
import vivere.academia.demo.repository.IUserRepository;
import vivere.academia.demo.service.interf.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) throws UserNotExistsException {
        if(userRepository.existsById(user.getId())){
            userRepository.save(user);
        }else{ //It should never reach the else line because it is prevented in the controller
            throw new UserNotExistsException();
        }
    }

    @Override
    public void deleteUserById(int userId) throws UserNotExistsException {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        }else{
            throw new UserNotExistsException();
        }
    }

    @Override
    public User findUserById(int userId) throws UserNotExistsException {
        return userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
