package vivere.academia.demo.service.interf;

import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;

import java.util.List;

public interface IClientService {
    void createClient(Client client);
    void deleteClientById(int clientId) throws ClientNotExistsException;
    Client findClientById(int clientId) throws ClientNotExistsException;
    List<Client> findAllClients();
}
