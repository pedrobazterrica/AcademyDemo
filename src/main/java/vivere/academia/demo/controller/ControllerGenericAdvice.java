package vivere.academia.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vivere.academia.demo.exceptions.ErrorMessage;
import vivere.academia.demo.exceptions.GenericWebException;

@ControllerAdvice
public class ControllerGenericAdvice {

    @ExceptionHandler(value = {GenericWebException.class})
    public ResponseEntity<ErrorMessage> userExists(GenericWebException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ErrorMessage.builder().code("404").message(ex.getMessage()).build());
    }

}
