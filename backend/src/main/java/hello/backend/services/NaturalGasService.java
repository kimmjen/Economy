package hello.backend.services;

import hello.backend.entity.NaturalGas;
import hello.backend.repository.NaturalGasRepository;
import hello.backend.services.paginated.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NaturalGasService {

    private final NaturalGasRepository naturalGasRepository;
    private final PaginatedService<NaturalGas> paginatedService;

    @Autowired
    public NaturalGasService(NaturalGasRepository naturalGasRepository, PaginatedService<NaturalGas> paginatedService) {
        this.naturalGasRepository = naturalGasRepository;
        this.paginatedService = paginatedService;
    }

    public List<NaturalGas> getAllData() {
        return naturalGasRepository.findAll();
    }

    public Page<NaturalGas> getData(int offset, int limit) {
        return paginatedService.getPaginatedData(offset, limit, Sort.Direction.DESC, "id", naturalGasRepository);
    }

    public NaturalGas getById(Long id) {
        return naturalGasRepository.findById(id).orElse(null);
    }

    public List<NaturalGas> getByDate(LocalDate date) {
        return naturalGasRepository.findByDate(date);
    }

    public List<NaturalGas> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return naturalGasRepository.findByDateBetween(startDate, endDate);
    }


}
