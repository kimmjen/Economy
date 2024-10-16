package hello.backend.repository;

import hello.backend.entity.Nasdaq100;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Nasdaq100Repository extends JpaRepository<Nasdaq100, Long> {
    List<Nasdaq100> findByDate(LocalDate date);
    List<Nasdaq100> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
