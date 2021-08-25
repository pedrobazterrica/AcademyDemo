package vivere.academia.demo.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotExistsException extends GenericWebException {

    public UserNotExistsException() {
        this.status = HttpStatus.NOT_FOUND.value();
        this.code = "01";
    }

    public String getMessage() {
        return "User Not Found";
    }
}
