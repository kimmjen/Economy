package hello.backend.services;

import hello.backend.entity.WtiOil;
import hello.backend.repository.WtiOilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WtiOilService {

    private final WtiOilRepository wti_oilRepository;

    @Autowired
    public WtiOilService(WtiOilRepository wti_oilRepository) {
        this.wti_oilRepository = wti_oilRepository;
    }
    public List<WtiOil> getAllData() {
        return wti_oilRepository.findAll();
    }

    public WtiOil getById(Long id) {
        return wti_oilRepository.findById(id).orElse(null);
    }

    public List<WtiOil> getByDate(LocalDate date) {
        return wti_oilRepository.findByDate(date);
    }

    public List<WtiOil> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return wti_oilRepository.findByDateBetween(startDate, endDate);
    }
}
