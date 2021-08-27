package vivere.academia.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CashBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    @JsonIgnore
    private Client client;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate releaseDate;
    @NotNull
    private String description;
    @NotNull
    @Column(columnDefinition="CHAR(1) CHECK (type IN ('D', 'C'))")
    private char type;
    private double value;

}
