package hello.backend.services;

import hello.backend.entity.Dollar;
import hello.backend.repository.DollarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DollarService {

    private final DollarRepository dollarRepository;

    @Autowired
    public DollarService(DollarRepository dollarRepository) {
        this.dollarRepository = dollarRepository;
    }

    public List<Dollar> getAllData() {
        return dollarRepository.findAll();
    }

    public Dollar getById(Long id) {
        return dollarRepository.findById(id).orElse(null);
    }

    public List<Dollar> getByDate(LocalDate date) {
        return dollarRepository.findByDate(date);
    }

    public List<Dollar> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return dollarRepository.findByDateBetween(startDate, endDate);
    }
}