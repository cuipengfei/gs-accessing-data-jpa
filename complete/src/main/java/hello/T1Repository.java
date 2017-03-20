package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface T1Repository extends PagingAndSortingRepository<T1, Long> {
  @Query(value = "select x.id from hello.T1 x where x.a = ?1")
  UUID getT1IdByABC(UUID a);
}
