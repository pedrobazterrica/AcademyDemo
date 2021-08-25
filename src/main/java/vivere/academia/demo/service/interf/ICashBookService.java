package vivere.academia.demo.service.interf;

import vivere.academia.demo.models.CashBook;

public interface ICashBookService {
    void createCashBook(CashBook cashBook);
    void findCashBookById(int cashBookId);
    void findAllCashBookByClientId(int clientId);
}
