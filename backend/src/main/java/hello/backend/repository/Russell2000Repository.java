package hello.backend.repository;

import hello.backend.entity.Russell2000;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Russell2000Repository extends JpaRepository<Russell2000, Long> {

    List<Russell2000> findByDate(LocalDate date);
    List<Russell2000> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
