package vivere.academia.demo.service.interf;

import vivere.academia.demo.dto.CashBookDTO;
import vivere.academia.demo.exceptions.CashBookNotExistsException;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.CashBook;

import java.util.List;

public interface ICashBookService {
    void createCashBook(CashBookDTO cashBookDTO) throws ClientNotExistsException;
    void deleteCashBookById(int cashBookId) throws CashBookNotExistsException;
    CashBook findCashBookById(int cashBookId) throws CashBookNotExistsException;
    List<CashBook> findAllCashBookByClientId(int clientId);
}
