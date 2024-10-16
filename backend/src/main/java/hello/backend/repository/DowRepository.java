package hello.backend.repository;

import hello.backend.entity.Dow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DowRepository extends JpaRepository<Dow, Long> {
    List<Dow> findByDate(LocalDate date);
    List<Dow> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

