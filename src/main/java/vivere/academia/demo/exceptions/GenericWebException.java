package vivere.academia.demo.exceptions;

import lombok.Data;

@Data
public abstract class GenericWebException extends Exception {
    int status;
    String code;
}