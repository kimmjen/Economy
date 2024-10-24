package hello.backend.services;

import hello.backend.entity.Russell2000;
import hello.backend.repository.Russell2000Repository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Russell2000Service {

    private final Russell2000Repository russell2000Repository;
    private final PaginatedService<Russell2000> paginatedService;

    @Autowired
    public Russell2000Service(Russell2000Repository russell2000Repository, PaginatedService<Russell2000> paginatedService) {
        this.russell2000Repository = russell2000Repository;
        this.paginatedService = paginatedService;
    }

    public List<Russell2000> getAllData() {
        return russell2000Repository.findAll();
    }

    public Page<Russell2000> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", russell2000Repository);
    }

    public Russell2000 getById(Long id) {
        return russell2000Repository.findById(id).orElse(null);
    }

    public List<Russell2000> getByDate(LocalDate date) {
        return russell2000Repository.findByDate(date);
    }

    public List<Russell2000> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return russell2000Repository.findByDateBetween(startDate, endDate);
    }
}
