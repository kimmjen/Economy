package hello.backend.controller;

import hello.backend.entity.Dow;
import hello.backend.services.DowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/dow")
public class DowController {

    private final DowService dowService;

    @Autowired
    public DowController(DowService dowService) {
        this.dowService = dowService;

    }
    @GetMapping
    public List<Dow> getAllData() {
        return dowService.getAllData();
    }

    @GetMapping("/{id}")
    public Dow getById(@PathVariable Long id) {
        return dowService.getById(id);
    }

    @GetMapping("/date")
    public List<Dow> getByDate(@RequestParam LocalDate date) {
        return dowService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Dow> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return dowService.getByDateRange(startDate, endDate);
    }
}
