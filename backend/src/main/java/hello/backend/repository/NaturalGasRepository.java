package hello.backend.repository;

import hello.backend.entity.NaturalGas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NaturalGasRepository extends JpaRepository<NaturalGas, Long> {
    List<NaturalGas> findByDate(LocalDate date);
    List<NaturalGas> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
