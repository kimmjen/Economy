package hello.backend.controller;

import hello.backend.entity.Nasdaq100;
import hello.backend.services.Nasdaq100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nasdaq100")
public class Nasdaq100Controller {

    private final Nasdaq100Service nasdaq100Service;

    @Autowired
    public Nasdaq100Controller(Nasdaq100Service nasdaq100Service) {
        this.nasdaq100Service = nasdaq100Service;
    }
    @GetMapping
    public List<Nasdaq100> getAllData() {
        return nasdaq100Service.getAllData();
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
