package vivere.academia.demo.exceptions;

import org.springframework.http.HttpStatus;

public class UserLoginAlreadyExistsException extends GenericWebException {

    public UserLoginAlreadyExistsException() {
        this.status = HttpStatus.IM_USED.value();
        this.code = "05";
    }

    public String getMessage() {
        return "Login Already in use";
    }
}