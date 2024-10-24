package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.entity.NaturalGas;
import hello.backend.services.NaturalGasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/natural-gas")
public class NaturalGasController {

    private final NaturalGasService naturalGasService;

    @Autowired
    public NaturalGasController(NaturalGasService naturalGasService) {
        this.naturalGasService = naturalGasService;
    }

    // 페이징된 데이터를 반환하는 엔드포인트
    @Operation(summary = "Get paginated dollar data")
    @GetMapping
    public Page<NaturalGas> getPaginatedDollars(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return naturalGasService.getData(offset, limit);
    }

    @GetMapping("/{id}")
    public NaturalGas getById(@PathVariable Long id) {
        return naturalGasService.getById(id);
    }

    @GetMapping("/date")
    public List<NaturalGas> getByDate(@RequestParam LocalDate date) {
        return naturalGasService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<NaturalGas> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return naturalGasService.getByDateRange(startDate, endDate);
    }
}
