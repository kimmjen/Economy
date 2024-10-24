package hello.backend.services;

import hello.backend.entity.Dollar;
import hello.backend.repository.DollarRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DollarService {

    private final DollarRepository dollarRepository;
    private final PaginatedService<Dollar> paginatedService;

    @Autowired
    public DollarService(DollarRepository dollarRepository, PaginatedService<Dollar> paginatedService) {
        this.dollarRepository = dollarRepository;
        this.paginatedService = paginatedService;
    }

    public List<Dollar> getAllData() {
        return dollarRepository.findAll();
    }

    // Use paginated service to get paginated and sorted data
    public Page<Dollar> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", dollarRepository);
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