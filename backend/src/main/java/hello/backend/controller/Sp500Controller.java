package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.entity.Sp500;
import hello.backend.services.Sp500Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sp500")
public class Sp500Controller {

    private final Sp500Service sp500Service;

    @Autowired
    public Sp500Controller(Sp500Service sp500Service) {
        this.sp500Service = sp500Service;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<Sp500> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return sp500Service.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public Sp500 getById(@PathVariable Long id) {
        return sp500Service.getById(id);
    }

    @GetMapping("/date")
    public List<Sp500> getByDate(@RequestParam LocalDate date) {
        return sp500Service.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Sp500> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return sp500Service.getByDateRange(startDate, endDate);
    }
}
