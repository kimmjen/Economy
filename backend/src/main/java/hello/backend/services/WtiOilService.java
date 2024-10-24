package hello.backend.services;

import hello.backend.entity.Treasury2yr;
import hello.backend.entity.WtiOil;
import hello.backend.repository.WtiOilRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WtiOilService {

    private final WtiOilRepository wti_oilRepository;
    private final PaginatedService<WtiOil> paginatedService;

    @Autowired
    public WtiOilService(WtiOilRepository wti_oilRepository, PaginatedService<WtiOil> paginatedService) {
        this.wti_oilRepository = wti_oilRepository;
        this.paginatedService = paginatedService;
    }
    public List<WtiOil> getAllData() {
        return wti_oilRepository.findAll();
    }

    public Page<WtiOil> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", wti_oilRepository);
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
