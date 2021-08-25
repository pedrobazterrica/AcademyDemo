package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.IClientRepository;
import vivere.academia.demo.service.interf.IClientService;

import java.util.List;
@Service
public class ClientServiceImpl implements IClientService {
    private final IClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteClientById(int clientId) throws ClientNotExistsException {
        if(clientRepository.existsById(clientId)){
            clientRepository.deleteById(clientId);
        }else{
            throw new ClientNotExistsException();
        }
    }

    @Override
    public Client findClientById(int clientId) throws ClientNotExistsException {
        return clientRepository.findById(clientId).orElseThrow(ClientNotExistsException::new);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
