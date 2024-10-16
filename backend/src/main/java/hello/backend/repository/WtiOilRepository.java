package hello.backend.repository;

import hello.backend.entity.WtiOil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WtiOilRepository extends JpaRepository<WtiOil, Long> {
    List<WtiOil> findByDate(LocalDate date);
    List<WtiOil> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
