package hello.backend.controller;

import hello.backend.entity.Dollar;
import hello.backend.entity.Gold;
import hello.backend.services.GoldService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gold")
public class GoldController {

    private final GoldService goldService;

    @Autowired
    public GoldController(GoldService goldService) {
        this.goldService = goldService;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<Gold> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return goldService.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public Gold getData(@PathVariable Long id) {
        return goldService.getById(id);
    }

    @GetMapping("/date")
    public List<Gold> getByDate(@RequestParam LocalDate date) {
        return goldService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Gold> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return goldService.getByDateRange(startDate, endDate);
    }
}
