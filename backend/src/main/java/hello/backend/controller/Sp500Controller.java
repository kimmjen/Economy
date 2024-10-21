package hello.backend.controller;

import hello.backend.entity.Sp500;
import hello.backend.services.Sp500Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Sp500> getAllData() {
        return sp500Service.getAllData();
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
