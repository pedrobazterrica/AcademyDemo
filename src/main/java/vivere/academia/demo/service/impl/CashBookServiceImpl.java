package vivere.academia.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.dto.CashBookDTO;
import vivere.academia.demo.exceptions.CashBookNotExistsException;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.CashBook;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.ICashBookRepository;
import vivere.academia.demo.repository.IClientRepository;
import vivere.academia.demo.service.interf.ICashBookService;

import java.util.List;
import java.util.Optional;

@Service
public class CashBookServiceImpl implements ICashBookService {
    private final ICashBookRepository cashBookRepository;
    private IClientRepository clientRepository;

    @Autowired
    public CashBookServiceImpl(ICashBookRepository cashBookRepository, IClientRepository clientRepository) {
        this.cashBookRepository = cashBookRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void createCashBook(CashBookDTO cashBookDTO) throws ClientNotExistsException {
        Optional<Client> client = clientRepository.findById(cashBookDTO.clientId);
        if(client.isPresent()){
            CashBook cashBook = CashBook.builder()
                                        .client(client.get()).description(cashBookDTO.description)
                                        .releaseDate(cashBookDTO.releaseDate)
                                        .type(cashBookDTO.type)
                                        .value(cashBookDTO.value)
                                        .build();
            cashBookRepository.save(cashBook);
        }else{
            throw new ClientNotExistsException();
        }
    }

    @Override
    public void deleteCashBookById(int cashBookId) throws CashBookNotExistsException {
        if(cashBookRepository.existsById(cashBookId)){
            cashBookRepository.deleteById(cashBookId);
        }else{
            throw new CashBookNotExistsException();
        }
    }

    @Override
    public CashBook findCashBookById(int cashBookId) throws CashBookNotExistsException {
        return cashBookRepository.findById(cashBookId).orElseThrow(CashBookNotExistsException::new);
    }

    @Override
    public List<CashBook> findAllCashBookByClientId(int clientId) {
        return cashBookRepository.findAllByClientId(clientId);
    }


}
