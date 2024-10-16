package hello.backend.services;

import hello.backend.entity.Gold;
import hello.backend.repository.GoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoldService {

    private final GoldRepository goldRepository;

    @Autowired
    public GoldService(GoldRepository goldRepository) {
        this.goldRepository = goldRepository;
    }

    public List<Gold> getAllData() {
        return goldRepository.findAll();
    }

    public Gold getById(Long id) {
        return goldRepository.findById(id).orElse(null);
    }

    public List<Gold> getByDate(LocalDate date) {
        return goldRepository.findByDate(date);
    }

    public List<Gold> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return goldRepository.findByDateBetween(startDate, endDate);
    }
}
