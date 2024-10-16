package hello.backend.services;

import hello.backend.entity.Treasury2yr;
import hello.backend.repository.Treasury2yrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Treasury2yrService {

    private final Treasury2yrRepository treasury_2yrRepository;

    @Autowired
    public Treasury2yrService(Treasury2yrRepository treasury_2yrRepository) {
        this.treasury_2yrRepository = treasury_2yrRepository;
    }

    public List<Treasury2yr> getAllData() {
        return treasury_2yrRepository.findAll();
    }

    public Treasury2yr getById(Long id) {
        return treasury_2yrRepository.findById(id).orElse(null);
    }

    public List<Treasury2yr> getByDate(LocalDate date) {
        return treasury_2yrRepository.findByDate(date);
    }

    public List<Treasury2yr> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return treasury_2yrRepository.findByDateBetween(startDate, endDate);
    }

}
