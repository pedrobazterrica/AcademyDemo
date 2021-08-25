package vivere.academia.demo.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    String code;
    String message;
}