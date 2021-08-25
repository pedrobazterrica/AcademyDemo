package vivere.academia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
}
