package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.entity.WtiOil;
import hello.backend.services.WtiOilService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wtioil")
public class WtiOilController {

    private final WtiOilService wtiOilService;

    @Autowired
    public WtiOilController(WtiOilService wtiOilService) {
        this.wtiOilService = wtiOilService;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<WtiOil > getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return wtiOilService.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public WtiOil getById(@PathVariable Long id) {
        return wtiOilService.getById(id);
    }

    @GetMapping("/date")
    public List<WtiOil> getByDate(@RequestParam LocalDate date) {
        return wtiOilService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<WtiOil> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return wtiOilService.getByDateRange(startDate, endDate);
    }
}
