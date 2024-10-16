package hello.backend.repository;

import hello.backend.entity.Dollar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DollarRepository extends JpaRepository<Dollar, Long> {
    List<Dollar> findByDate(LocalDate date);
    List<Dollar> findByDateBetween(LocalDate startDate, LocalDate endDate);
}