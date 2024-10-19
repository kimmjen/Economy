package hello.backend.repository;

import hello.backend.entity.EconomicIndicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EconomicIndicatorsRepository extends JpaRepository<EconomicIndicators, Long> {
    // 특정 eventType을 가진 EconomicIndicators를 모두 조회
    List<EconomicIndicators> findByEventType(String eventType);


    // 특정 날짜의 데이터를 조회
    List<EconomicIndicators> findByEventDate(LocalDate eventDate);

    // 특정 날짜 범위의 데이터를 조회하는 쿼리
    @Query("SELECT e FROM EconomicIndicators e WHERE e.eventDate BETWEEN :startDate AND :endDate")
    List<EconomicIndicators> findByEventDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
