package vivere.academia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);
}
