package hello.backend.services;

import hello.backend.entity.EconomicIndicators;
import hello.backend.repository.EconomicIndicatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EconomicIndicatorsService {

    private final EconomicIndicatorsRepository economicIndicatorsRepository;

    @Autowired
    public EconomicIndicatorsService(EconomicIndicatorsRepository economicIndicatorsRepository) {
        this.economicIndicatorsRepository = economicIndicatorsRepository;
    }

    // 모든 지표 데이터를 가져오기
    public List<EconomicIndicators> getAllEconomicIndicators() {
        return economicIndicatorsRepository.findAll();
    }

    // 특정 ID로 지표 데이터 조회
    public Optional<EconomicIndicators> getEconomicIndicatorById(Long id) {
        return economicIndicatorsRepository.findById(id);
    }

    // 새로운 경제 지표 데이터 저장
    public EconomicIndicators saveEconomicIndicator(EconomicIndicators economicIndicator) {
        return economicIndicatorsRepository.save(economicIndicator);
    }

    // 특정 ID로 지표 데이터 삭제
    public void deleteEconomicIndicator(Long id) {
        economicIndicatorsRepository.deleteById(id);
    }

    // 특정 eventType으로 지표 데이터 조회
    public List<EconomicIndicators> getEconomicIndicatorsByEventType(String eventType) {
        return economicIndicatorsRepository.findByEventType(eventType);
    }

    // 특정 날짜의 데이터를 조회
    public List<EconomicIndicators> getEconomicIndicatorsByDate(LocalDate date) {
        return economicIndicatorsRepository.findByEventDate(date);
    }

    // 특정 날짜 범위의 데이터를 조회
    public List<EconomicIndicators> getEconomicIndicatorsByDateRange(LocalDate startDate, LocalDate endDate) {
        return economicIndicatorsRepository.findByEventDateBetween(startDate, endDate);
    }
}
