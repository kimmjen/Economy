package hello.backend.controller;

import hello.backend.entity.Gold;
import hello.backend.services.GoldService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Gold> getAllData() {
        return goldService.getAllData();
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
