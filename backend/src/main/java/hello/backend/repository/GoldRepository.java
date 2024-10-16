package hello.backend.repository;

import hello.backend.entity.Gold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GoldRepository extends JpaRepository<Gold, Long> {
    List<Gold> findByDate(LocalDate date);
    List<Gold> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
