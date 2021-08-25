package vivere.academia.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.service.interf.IClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final IClientService clientService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientController(IClientService clientService, ObjectMapper objectMapper) {
        this.clientService = clientService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) throws ClientNotExistsException {
        return clientService.findClientById(id);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.findAllClients();
    }

    @PostMapping
    public void createClient(@RequestBody Client client){
        clientService.createClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable int id) throws ClientNotExistsException {
        clientService.deleteClientById(id);
    }

}
