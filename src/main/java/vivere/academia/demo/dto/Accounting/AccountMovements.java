package vivere.academia.demo.dto.Accounting;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AccountMovements {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public LocalDate releaseDate;
    public String description;
    public char type;
    public double value;
    public double balance;
}
