package hello.backend.services;

import hello.backend.entity.Dow;
import hello.backend.repository.DowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DowService {

    private final DowRepository dowRepository;

    @Autowired
    public DowService(DowRepository dowRepository) {
        this.dowRepository = dowRepository;
    }

    public List<Dow> getAllData() {
        return dowRepository.findAll();
    }

    public Dow getById(Long id) {
        return dowRepository.findById(id).orElse(null);
    }

    public List<Dow> getByDate(LocalDate date) {
        return dowRepository.findByDate(date);
    }

    public List<Dow> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return dowRepository.findByDateBetween(startDate, endDate);
    }
}
