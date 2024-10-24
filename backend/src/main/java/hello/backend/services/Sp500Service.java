package hello.backend.services;

import hello.backend.entity.Sp500;
import hello.backend.repository.Sp500Repository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Sp500Service {

    private final Sp500Repository sp500Repository;
    private final PaginatedService<Sp500> paginatedService;

    @Autowired
    public Sp500Service(Sp500Repository sp500Repository, PaginatedService<Sp500> paginatedService) {
        this.sp500Repository = sp500Repository;
        this.paginatedService = paginatedService;
    }

    public List<Sp500> getAllData() {
        return sp500Repository.findAll();
    }

    public Page<Sp500> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", sp500Repository);
    }

    public Sp500 getById(Long id) {
        return sp500Repository.findById(id).orElse(null);
    }

    public List<Sp500> getByDate(LocalDate date) {
        return sp500Repository.findByDate(date);
    }

    public List<Sp500> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return sp500Repository.findByDateBetween(startDate, endDate);
    }
}
