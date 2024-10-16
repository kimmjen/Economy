package hello.backend.controller;

import hello.backend.entity.NaturalGas;
import hello.backend.services.NaturalGasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/natural-gas")
public class NaturalGasController {

    private final NaturalGasService naturalGasService;

    @Autowired
    public NaturalGasController(NaturalGasService naturalGasService) {
        this.naturalGasService = naturalGasService;
    }

    @GetMapping
    public List<NaturalGas> getAllData() {
        return naturalGasService.getAllData();
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
