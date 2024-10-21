package hello.backend.controller;

import hello.backend.entity.Treasury2yr;
import hello.backend.services.Treasury2yrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/treasury2yr")
public class Treasury2yrController {

    private final Treasury2yrService treasury2yrService;

    @Autowired
    public Treasury2yrController(Treasury2yrService treasury2yrService) {
        this.treasury2yrService = treasury2yrService;
    }

    @GetMapping
    public List<Treasury2yr> getAllData() {
        return treasury2yrService.getAllData();
    }

    @GetMapping("/{id}")
    public Treasury2yr getById(@PathVariable Long id) {
        return treasury2yrService.getById(id);
    }

    @GetMapping("/date")
    public List<Treasury2yr> getByDate(@RequestParam LocalDate date) {
        return treasury2yrService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Treasury2yr> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return treasury2yrService.getByDateRange(startDate, endDate);
    }
}
