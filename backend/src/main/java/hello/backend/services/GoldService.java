package hello.backend.services;

import hello.backend.entity.Gold;
import hello.backend.repository.GoldRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoldService {

    private final GoldRepository goldRepository;
    private final PaginatedService<Gold> paginatedService;

    @Autowired
    public GoldService(GoldRepository goldRepository, PaginatedService<Gold> paginatedService) {
        this.goldRepository = goldRepository;
        this.paginatedService = paginatedService;
    }

    public List<Gold> getAllData() {
        return goldRepository.findAll();
    }

    public Page<Gold> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", goldRepository);
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
