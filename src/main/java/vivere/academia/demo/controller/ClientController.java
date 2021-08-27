package vivere.academia.demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import vivere.academia.demo.dto.Accounting.AccountingResponseDTO;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.CustomRepository.utils.SearchCriteria;
import vivere.academia.demo.service.interf.IAccountingService;
import vivere.academia.demo.service.interf.IClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/client")
public class ClientController {
    private final IClientService clientService;
    private final IAccountingService accountingService;
    private final ObjectMapper objectMapper;


    @Autowired
    public ClientController(IClientService clientService, IAccountingService accountingService, ObjectMapper objectMapper) {
        this.clientService = clientService;
        this.accountingService = accountingService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) throws ClientNotExistsException {
        Client client = clientService.findClientById(id);
        System.out.println(client.toString());
        return client;
    }

//    @GetMapping
//    public List<Client> getAllClients(){
//        return clientService.findAllClients();
//    }

    @PostMapping
    public void createClient(@RequestBody Client client){
        clientService.createClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable int id) throws ClientNotExistsException {
        clientService.deleteClientById(id);
    }

    @GetMapping("/{id}/accounting")
    public AccountingResponseDTO getAccountingForClientId(
            @PathVariable int id,
            @RequestParam("firstDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDate,
            @RequestParam ("lastDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate lastDate
    ) throws ClientNotExistsException {
        return accountingService.getAccountingForClientId(id, firstDate, lastDate);
    }

    @GetMapping
    @ResponseBody
    public List<Client> findAll(@RequestParam(value = "search", required = false) String search) {
        return clientService.findAllClients(Optional.ofNullable(search));
    }

}
