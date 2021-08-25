package vivere.academia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.CashBook;

@Repository
public interface ICashBookRepository extends JpaRepository<CashBook, Integer> {
}
