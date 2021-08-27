package vivere.academia.demo.exceptions;

import org.springframework.http.HttpStatus;


public class CashBookNotExistsException extends GenericWebException {
        public CashBookNotExistsException() {
            this.status = HttpStatus.NOT_FOUND.value();
            this.code = "01";
        }
        public String getMessage() {
            return "CashBook Not Found";
        }
}

