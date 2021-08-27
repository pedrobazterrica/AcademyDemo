package vivere.academia.demo.dto.Accounting;

import lombok.Builder;
import lombok.Data;
import vivere.academia.demo.models.Client;

import java.util.List;

public class AccountingResponseDTO {
    public int id;
    public String name;
    public String cpfCnpj;
    public String phone;
    public List<AccountMovements> account;
}
