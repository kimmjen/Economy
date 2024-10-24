package hello.backend.controller;

import hello.backend.entity.Dollar;
import hello.backend.services.DollarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dollar")
public class DollarController {

    private final DollarService dollarService;

    @Autowired
    public DollarController(DollarService dollarService) {
        this.dollarService = dollarService;
    }


    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<Dollar> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return dollarService.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public Dollar getById(@PathVariable Long id) {
        return dollarService.getById(id);
    }

    @GetMapping("/date")
    public List<Dollar> getByDate(
            @Parameter(description = "Date in the format yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dollarService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Dollar> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return dollarService.getByDateRange(startDate, endDate);
    }
}