package hello.backend.controller;

import hello.backend.entity.WtiOil;
import hello.backend.services.WtiOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wtioil")
public class WtiOilController {

    private final WtiOilService wtiOilService;

    @Autowired
    public WtiOilController(WtiOilService wtiOilService) {
        this.wtiOilService = wtiOilService;
    }

    @GetMapping
    public List<WtiOil> getAllData() {
        return wtiOilService.getAllData();
    }

    @GetMapping("/{id}")
    public WtiOil getById(@PathVariable Long id) {
        return wtiOilService.getById(id);
    }

    @GetMapping("/date")
    public List<WtiOil> getByDate(@RequestParam LocalDate date) {
        return wtiOilService.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<WtiOil> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return wtiOilService.getByDateRange(startDate, endDate);
    }
}
