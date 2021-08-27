package vivere.academia.demo.dto;

import vivere.academia.demo.models.CashBook;
import java.time.LocalDateTime;
import java.util.List;

public class ClientDTO {
    private int id;
    private List<CashBook> cashBookList;
    private LocalDateTime registerDate;
    private String name;
    private String cpfCnpj;
    private String street;
    private String city;
    private char[] state;
    private char[] postalCod;
    private String phone;
    private String email;
}
