package vivere.academia.demo.service.interf;

import vivere.academia.demo.dto.Accounting.AccountingResponseDTO;
import vivere.academia.demo.exceptions.ClientNotExistsException;

import java.time.LocalDate;

public interface IAccountingService {
    AccountingResponseDTO getAccountingForClientId(int clientId, LocalDate firstDate, LocalDate lastDate) throws ClientNotExistsException;
}
