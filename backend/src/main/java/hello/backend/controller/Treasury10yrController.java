package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.entity.Treasury10yr;
import hello.backend.services.Treasury10yrService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/treasury10yr")
public class Treasury10yrController {

    private final Treasury10yrService treasury10yrService;

    @Autowired
    public Treasury10yrController(Treasury10yrService treasury10yrService) {
        this.treasury10yrService = treasury10yrService;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<Treasury10yr> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return treasury10yrService.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public Treasury10yr getById(@PathVariable Long id) {
        return treasury10yrService.getById(id);
    }

    @GetMapping("/date")
    public List<Treasury10yr> getByDate(@RequestParam LocalDate date) {
        return treasury10yrService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Treasury10yr> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return treasury10yrService.getByDateRange(startDate, endDate);
    }
}
