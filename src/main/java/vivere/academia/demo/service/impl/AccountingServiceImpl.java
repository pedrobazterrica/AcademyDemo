package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.dto.Accounting.AccountMovements;
import vivere.academia.demo.dto.Accounting.AccountingResponseDTO;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.CashBook;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.ICashBookRepository;
import vivere.academia.demo.repository.IClientRepository;
import vivere.academia.demo.service.interf.IAccountingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class AccountingServiceImpl implements IAccountingService {
    private IClientRepository clientRepository;
    private ICashBookRepository cashBookRepository;

    @Autowired
    public AccountingServiceImpl(IClientRepository clientRepository, ICashBookRepository cashBookRepository){
        this.clientRepository = clientRepository;
        this.cashBookRepository = cashBookRepository;
    }
    @Override
    public AccountingResponseDTO getAccountingForClientId(int clientId, LocalDate firstDate, LocalDate lastDate) throws ClientNotExistsException {
        if(clientRepository.existsById(clientId)){
            Client client = clientRepository.getById(clientId);
            List<CashBook> cashBookList = cashBookRepository.findAllByClientIdAndReleaseDateBetween(clientId, firstDate, lastDate);
            List<AccountMovements> accountMovementsList = new ArrayList<>();
            if(!cashBookList.isEmpty()){
                double startBalance = 0;
                for (CashBook cashBook:cashBookList) {
                    AccountMovements accountMovements = new AccountMovements();
                    accountMovements.description = cashBook.getDescription();
                    accountMovements.releaseDate = cashBook.getReleaseDate();
                    accountMovements.type = cashBook.getType();
                    accountMovements.value = cashBook.getValue();
                    if(cashBook.getType() == 'D'){
                        startBalance-=accountMovements.value;
                    }else{
                        startBalance+=accountMovements.value;
                    }
                    accountMovements.balance = startBalance;
                    accountMovementsList.add(accountMovements);
                }
            }
            AccountingResponseDTO accountingResponseDTO = new AccountingResponseDTO();
            accountingResponseDTO.id = client.getId();
            accountingResponseDTO.name = client.getName();
            accountingResponseDTO.cpfCnpj = client.getCpfCnpj();
            accountingResponseDTO.phone = client.getPhone();
            accountingResponseDTO.account = accountMovementsList;
            return accountingResponseDTO;
        }else{
            throw new ClientNotExistsException();
        }
    }
}
