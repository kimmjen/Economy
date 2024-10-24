package hello.backend.services;

import hello.backend.entity.Dollar;
import hello.backend.entity.Dow;
import hello.backend.repository.DowRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DowService {

    private final DowRepository dowRepository;
    private final PaginatedService<Dow> paginatedService;

    @Autowired
    public DowService(DowRepository dowRepository, PaginatedService<Dow> paginatedService) {
        this.dowRepository = dowRepository;
        this.paginatedService = paginatedService;
    }

    public List<Dow> getAllData() {
        return dowRepository.findAll();
    }

    public Page<Dow> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", dowRepository);
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
