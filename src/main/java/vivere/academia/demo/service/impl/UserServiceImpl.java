package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.models.User;
import vivere.academia.demo.repository.IUserRepository;
import vivere.academia.demo.service.interf.IUserService;

import java.util.HashSet;
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
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(int userId) {

    }

    @Override
    public User findUserById(int userId) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
