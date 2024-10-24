package hello.backend.services;

import hello.backend.entity.Treasury2yr;
import hello.backend.repository.Treasury2yrRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Treasury2yrService {

    private final Treasury2yrRepository treasury_2yrRepository;
    private final PaginatedService<Treasury2yr> paginatedService;

    @Autowired
    public Treasury2yrService(Treasury2yrRepository treasury_2yrRepository, PaginatedService<Treasury2yr> paginatedService) {
        this.treasury_2yrRepository = treasury_2yrRepository;
        this.paginatedService = paginatedService;
    }

    public List<Treasury2yr> getAllData() {
        return treasury_2yrRepository.findAll();
    }

    public Page<Treasury2yr> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", treasury_2yrRepository);
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
