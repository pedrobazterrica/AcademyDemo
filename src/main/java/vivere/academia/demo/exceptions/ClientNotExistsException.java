package vivere.academia.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ClientNotExistsException extends GenericWebException {

    public ClientNotExistsException() {
        this.status = HttpStatus.NOT_FOUND.value();
        this.code = "01";
    }

    public String getMessage() {
        return "Client Not Found";
    }
}