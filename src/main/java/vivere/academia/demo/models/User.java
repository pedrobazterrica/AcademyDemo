package vivere.academia.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Test JSON
/*

{
    "name":"pedro",
    "registerDate":"08-24-2021",
    "login":"pedrobaz",
    "passwd":"pedrocapo",
    "phone":"123456",
    "email":"pedrocapo@mail.com",
    "profile":"A",
    "status":"A"
}
*/

@Data
@Entity
@Table(name = "users") //Changed table name because 'user' is a reserved word
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime registerDate;
    @NotNull
    @Column(length = 30)
    private String name;
    @NotNull
    @Column(length = 15)
    private String login;
    @NotNull
    @Column(length = 10)
    private String passwd;
    @Column(length = 11)
    private String phone;
    @Column(length = 100)
    private String email;
    @NotNull
    @Column(columnDefinition="CHAR(1)")
    private char profile;
    @NotNull
    @Column(columnDefinition="CHAR(1)")
    private char status;
}
