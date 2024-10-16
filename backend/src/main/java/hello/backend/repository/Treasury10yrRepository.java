package hello.backend.repository;

import hello.backend.entity.Treasury10yr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Treasury10yrRepository extends JpaRepository<Treasury10yr, Long> {
    List<Treasury10yr> findByDate(LocalDate date);
    List<Treasury10yr> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
