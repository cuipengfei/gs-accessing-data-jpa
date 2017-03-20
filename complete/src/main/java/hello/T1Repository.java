package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface T1Repository extends PagingAndSortingRepository<T1, Long> {
  T1 findBySomeField(@Param("someField") UUID someField);
}
