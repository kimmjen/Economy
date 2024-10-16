package hello.backend.services;

import hello.backend.entity.Sp500;
import hello.backend.repository.Sp500Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Sp500Service {

    private final Sp500Repository sp500Repository;

    @Autowired
    public Sp500Service(Sp500Repository sp500Repository) {
        this.sp500Repository = sp500Repository;
    }

    public List<Sp500> getAllData() {
        return sp500Repository.findAll();
    }

    public Sp500 getById(Long id) {
        return sp500Repository.findById(id).orElse(null);
    }

    public List<Sp500> getByDate(LocalDate date) {
        return sp500Repository.findByDate(date);
    }

    public List<Sp500> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return sp500Repository.findByDateBetween(startDate, endDate);
    }
}
