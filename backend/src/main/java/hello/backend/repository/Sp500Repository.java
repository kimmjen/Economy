package hello.backend.repository;

import hello.backend.entity.Sp500;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Sp500Repository extends JpaRepository<Sp500, Long> {
    List<Sp500> findByDate(LocalDate date);
    List<Sp500> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
