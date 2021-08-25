package vivere.academia.demo.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "client")
    private List<CashBook> cashBookList;

    private LocalDateTime registerDate;

    //You could also use @Column(nullable = false)
    //The difference between that and @NotNull is that with what I used the validation is done by hibernate
    //and if you send a null value the query isn't even sent to the database (even though the column also has the notnull constraint)
    @NotNull
    @Column(length = 30)
    private String name;

    @NotNull
    @Column(length = 14)
    @Size(min = 14, max = 14)
    private String cpfCnpj;

    @NotNull
    @Column(length = 50)
    private String street;

    @NotNull
    @Column(length = 40)
    private String city;

    @NotNull
    @Column(columnDefinition="CHAR(2)")
    private char[] state;

    @NotNull
    @Column(columnDefinition="CHAR(8)")
    private char[] postalCod;

    @Column(length = 11)
    private String phone;

    @Column(length = 100)
    private String email;
}
