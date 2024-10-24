package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.entity.Nasdaq100;
import hello.backend.services.Nasdaq100Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/nasdaq100")
public class Nasdaq100Controller {

    private final Nasdaq100Service nasdaq100Service;

    @Autowired
    public Nasdaq100Controller(Nasdaq100Service nasdaq100Service) {
        this.nasdaq100Service = nasdaq100Service;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<Nasdaq100> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return nasdaq100Service.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public Nasdaq100 getById(@PathVariable Long id) {
        return nasdaq100Service.getById(id);
    }

    @GetMapping("/date")
    public List<Nasdaq100> getByDate(@RequestParam LocalDate date) {
        return nasdaq100Service.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Nasdaq100> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return nasdaq100Service.getByDateRange(startDate, endDate);
    }

}
