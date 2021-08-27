package vivere.academia.demo.repository.CustomRepository;

import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.Client;
import vivere.academia.demo.repository.CustomRepository.utils.ClientSearchQueryCriteriaConsumer;
import vivere.academia.demo.repository.CustomRepository.utils.SearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ClientRepositoryDAO implements IClientRepositoryDAOCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> searchClient(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root r = query.from(Client.class);

        Predicate predicate = builder.conjunction();

        ClientSearchQueryCriteriaConsumer searchConsumer = new ClientSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        List<Client> result = entityManager.createQuery(query).getResultList();
        return result;
    }

}
