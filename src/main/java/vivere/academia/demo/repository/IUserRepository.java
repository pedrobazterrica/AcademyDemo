package vivere.academia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}
