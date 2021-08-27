package vivere.academia.demo.service.interf;

import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    void createClient(Client client);
    void deleteClientById(int clientId) throws ClientNotExistsException;
    Client findClientById(int clientId) throws ClientNotExistsException;
    List<Client> findAllClients(Optional<String> search);
}
