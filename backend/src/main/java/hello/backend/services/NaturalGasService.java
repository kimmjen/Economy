package hello.backend.services;

import hello.backend.entity.NaturalGas;
import hello.backend.repository.NaturalGasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NaturalGasService {

    private final NaturalGasRepository naturalGasRepository;

    @Autowired
    public NaturalGasService(NaturalGasRepository naturalGasRepository) {
        this.naturalGasRepository = naturalGasRepository;
    }

    public List<NaturalGas> getAllData() {
        return naturalGasRepository.findAll();
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
