package vivere.academia.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class CashBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

    private LocalDate releaseDate;
    private String description;
    private char type;
    private double value;

}
