package hello.backend.services;

import hello.backend.entity.Treasury10yr;
import hello.backend.repository.Treasury10yrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Treasury10yrService {

    private final Treasury10yrRepository treasury_10yrRepository;

    @Autowired
    public Treasury10yrService(Treasury10yrRepository treasury_10yrRepository) {
        this.treasury_10yrRepository = treasury_10yrRepository;
    }

    public List<Treasury10yr> getAllData() {
        return treasury_10yrRepository.findAll();
    }

    public Treasury10yr getById(Long id) {
        return treasury_10yrRepository.findById(id).orElse(null);
    }

    public List<Treasury10yr> getByDate(LocalDate date) {
        return treasury_10yrRepository.findByDate(date);
    }

    public List<Treasury10yr> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return treasury_10yrRepository.findByDateBetween(startDate, endDate);
    }

}
