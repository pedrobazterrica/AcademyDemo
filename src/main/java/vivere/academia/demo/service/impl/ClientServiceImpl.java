package vivere.academia.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivere.academia.demo.exceptions.ClientNotExistsException;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.CustomRepository.IClientRepositoryDAOCustom;
import vivere.academia.demo.repository.CustomRepository.utils.SearchCriteria;
import vivere.academia.demo.repository.IClientRepository;
import vivere.academia.demo.service.interf.IClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements IClientService {
    private final IClientRepository clientRepository;
    private final IClientRepositoryDAOCustom clientDAO;

    @Autowired
    public ClientServiceImpl(IClientRepository clientRepository, IClientRepositoryDAOCustom clientDAO) {
        this.clientRepository = clientRepository;
        this.clientDAO = clientDAO;
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
    public List<Client> findAllClients(Optional<String> search) {
        if(search.isPresent()){
            List<SearchCriteria> params = new ArrayList<SearchCriteria>();
            if (search != null) {
                Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
                Matcher matcher = pattern.matcher(search + ",");
                while (matcher.find()) {
                    params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
            return clientDAO.searchClient(params);
        }else{
            return clientRepository.findAll();
        }
    }
}
