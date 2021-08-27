package vivere.academia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivere.academia.demo.models.CashBook;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICashBookRepository extends JpaRepository<CashBook, Integer> {
    List<CashBook> findAllByClientId(int clientId);
    List<CashBook> findAllByClientIdAndReleaseDateBetween(int clientId, LocalDate firstDate, LocalDate lastDate);
}
