package hello.backend.controller;

import hello.backend.entity.EconomicIndicators;
import hello.backend.services.EconomicIndicatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/economic-indicators")
public class EconomicIndicatorsController {

    private final EconomicIndicatorsService economicIndicatorsService;

    @Autowired
    public EconomicIndicatorsController(EconomicIndicatorsService economicIndicatorsService) {
        this.economicIndicatorsService = economicIndicatorsService;
    }

    // 모든 경제 지표 데이터를 조회하는 API
    @GetMapping
    public List<EconomicIndicators> getAllEconomicIndicators() {
        return economicIndicatorsService.getAllEconomicIndicators();
    }

    // 특정 ID로 경제 지표 데이터를 조회하는 API
    @GetMapping("/{id}")
    public Optional<EconomicIndicators> getEconomicIndicatorById(@PathVariable Long id) {
        return economicIndicatorsService.getEconomicIndicatorById(id);
    }

    // 특정 날짜의 경제 지표 데이터를 조회하는 API
    @GetMapping("/date/{date}")
    public List<EconomicIndicators> getEconomicIndicatorsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date); // 문자열을 LocalDate로 변환
        return economicIndicatorsService.getEconomicIndicatorsByDate(localDate);
    }

    // 특정 날짜 범위의 경제 지표 데이터를 조회하는 API
    @GetMapping("/date-range")
    public List<EconomicIndicators> getEconomicIndicatorsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return economicIndicatorsService.getEconomicIndicatorsByDateRange(start, end);
    }

    // 새로운 경제 지표 데이터를 추가하는 API
    @PostMapping
    public EconomicIndicators createEconomicIndicator(@RequestBody EconomicIndicators economicIndicator) {
        return economicIndicatorsService.saveEconomicIndicator(economicIndicator);
    }

    // 특정 ID의 경제 지표 데이터를 삭제하는 API
    @DeleteMapping("/{id}")
    public void deleteEconomicIndicator(@PathVariable Long id) {
        economicIndicatorsService.deleteEconomicIndicator(id);
    }

    // 특정 eventType으로 경제 지표 데이터를 조회하는 API
    @GetMapping("/type/{eventType}")
    public List<EconomicIndicators> getEconomicIndicatorsByEventType(@PathVariable String eventType) {
        return economicIndicatorsService.getEconomicIndicatorsByEventType(eventType);
    }
}
