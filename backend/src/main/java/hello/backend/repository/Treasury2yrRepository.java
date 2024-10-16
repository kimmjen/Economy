package hello.backend.repository;

import hello.backend.entity.Treasury2yr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Treasury2yrRepository extends JpaRepository<Treasury2yr, Long> {
    List<Treasury2yr> findByDate(LocalDate date);
    List<Treasury2yr> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
