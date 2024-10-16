package hello.backend.controller;

import hello.backend.entity.Russell2000;
import hello.backend.services.Russell2000Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/russell2000")
public class Russell2000Controller {

    private final Russell2000Service russell2000Service;

    @Autowired
    public Russell2000Controller(Russell2000Service russell2000Service) {
        this.russell2000Service = russell2000Service;
    }

    @GetMapping
    public List<Russell2000> getAllData() {
        return russell2000Service.getAllData();
    }

    @GetMapping("/{id}")
    public Russell2000 getById(@PathVariable Long id) {
        return russell2000Service.getById(id);
    }

    @GetMapping("/date")
    public List<Russell2000> getByDate(@RequestParam LocalDate date) {
        return russell2000Service.getByDate(date);
    }

    @GetMapping("/date-range")
    public List<Russell2000> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return russell2000Service.getByDateRange(startDate, endDate);
    }
}
