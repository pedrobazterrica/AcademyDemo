package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.dto.LoginRequestDTO;
import vivere.academia.demo.exceptions.UserLoginAlreadyExistsException;
import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.models.User;
import vivere.academia.demo.repository.IUserRepository;
import vivere.academia.demo.service.interf.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) throws UserLoginAlreadyExistsException {
        if(userRepository.existsByLogin(user.getLogin())){
            throw new UserLoginAlreadyExistsException();
        }else {
            userRepository.save(user);
        }
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
    public List<User> findByQuery(Optional<String> name, Optional<String> email){
        if(name.isPresent() && email.isPresent()){
            return userRepository.findByNameAndEmail(name.get(), email.get());
        }else if(name.isPresent()){
            return userRepository.findByName(name.get());
        }else if(email.isPresent()){
            return userRepository.findByEmail(email.get());
        }else {
            return userRepository.findAll();
        }
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) throws UserNotExistsException {
        Optional<User> user = userRepository.findByLogin(loginRequestDTO.login);
        if(user.isPresent()){
            System.out.println(user.get());
            if(user.get().getPasswd().equals(loginRequestDTO.password) && user.get().getStatus() == 'A'){
                return "Login Successful";
            }else if(user.get().getStatus() == 'C'){
                return "Status is Cancelled, Rejected Login";
            }else{
                return "Wrong Password";
            }
        }else{
            throw new UserNotExistsException();
        }
    }


}
