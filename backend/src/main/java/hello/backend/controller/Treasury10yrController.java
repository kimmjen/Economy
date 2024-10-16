package hello.backend.controller;

import hello.backend.entity.Treasury10yr;
import hello.backend.services.Treasury10yrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/treasury10yr")
public class Treasury10yrController {

    private final Treasury10yrService treasury10yrService;

    @Autowired
    public Treasury10yrController(Treasury10yrService treasury10yrService) {
        this.treasury10yrService = treasury10yrService;
    }

    @GetMapping
    public List<Treasury10yr> getAllData() {
        return treasury10yrService.getAllData();
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
