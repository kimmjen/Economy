package hello.backend.services;

import hello.backend.entity.Nasdaq100;
import hello.backend.repository.Nasdaq100Repository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Nasdaq100Service {

    private final Nasdaq100Repository nasdaq100Repository;
    private final PaginatedService<Nasdaq100> paginatedService;

    @Autowired
    public Nasdaq100Service(Nasdaq100Repository nasdaq100Repository, PaginatedService<Nasdaq100> paginatedService) {
        this.nasdaq100Repository = nasdaq100Repository;
        this.paginatedService = paginatedService;
    }

    public List<Nasdaq100> getAllData() {
        return nasdaq100Repository.findAll();
    }

    public Page<Nasdaq100> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", nasdaq100Repository);
    }

    public Nasdaq100 getById(Long id) {
        return nasdaq100Repository.findById(id).orElse(null);
    }

    public List<Nasdaq100> getByDate(LocalDate date) {
        return nasdaq100Repository.findByDate(date);
    }

    public List<Nasdaq100> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return nasdaq100Repository.findByDateBetween(startDate, endDate);
    }

}
