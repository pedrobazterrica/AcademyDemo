package vivere.academia.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vivere.academia.demo.dto.LoginRequestDTO;
import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.service.interf.IUserService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String login(@RequestBody LoginRequestDTO loginRequestDto) throws UserNotExistsException {
        return userService.login(loginRequestDto);
    }
}
