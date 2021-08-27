package vivere.academia.demo.repository.CustomRepository;

import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.CustomRepository.utils.SearchCriteria;

import java.util.List;

public interface IClientRepositoryDAOCustom {
    List<Client> searchClient(List<SearchCriteria> params);
}
