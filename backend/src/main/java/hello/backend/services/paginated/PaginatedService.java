package hello.backend.services.paginated;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PaginatedService<T> {

    public Page<T> getPaginatedData(int offset, int limit, Sort.Direction direction, String sortBy, JpaRepository<T, Long> repository) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(direction, sortBy));
        return repository.findAll(pageable);
    }
}
