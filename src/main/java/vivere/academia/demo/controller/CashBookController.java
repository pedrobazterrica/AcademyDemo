package vivere.academia.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vivere.academia.demo.dto.CashBookDTO;
import vivere.academia.demo.exceptions.CashBookNotExistsException;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.CashBook;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.service.interf.ICashBookService;
import vivere.academia.demo.service.interf.IClientService;

import java.util.List;

@RestController
@RequestMapping("/cashbook")
public class CashBookController {
    private final ICashBookService cashBookService;

    @Autowired
    public CashBookController(ICashBookService cashBookService) {
        this.cashBookService = cashBookService;
    }

    @GetMapping("/{id}")
    public CashBook getCashBookById(@PathVariable int id) throws CashBookNotExistsException {
        return cashBookService.findCashBookById(id);
    }

    @GetMapping
    public List<CashBook> getCashBookByClientId(@RequestParam int clientId) throws ClientNotExistsException{
        return cashBookService.findAllCashBookByClientId(clientId);
    }

    @PostMapping
    public void createClient(@RequestBody CashBookDTO cashBookDTO) throws ClientNotExistsException {
        cashBookService.createCashBook(cashBookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCashBookById(@PathVariable int id) throws CashBookNotExistsException {
        cashBookService.deleteCashBookById(id);
    }

}
