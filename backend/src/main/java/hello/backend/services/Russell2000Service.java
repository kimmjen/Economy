package hello.backend.services;

import hello.backend.entity.Russell2000;
import hello.backend.repository.Russell2000Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Russell2000Service {

    private final Russell2000Repository russell2000Repository;

    @Autowired
    public Russell2000Service(Russell2000Repository russell2000Repository) {
        this.russell2000Repository = russell2000Repository;
    }

    public List<Russell2000> getAllData() {
        return russell2000Repository.findAll();
    }

    public Russell2000 getById(Long id) {
        return russell2000Repository.findById(id).orElse(null);
    }

    public List<Russell2000> getByDate(LocalDate date) {
        return russell2000Repository.findByDate(date);
    }

    public List<Russell2000> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return russell2000Repository.findByDateBetween(startDate, endDate);
    }
}
