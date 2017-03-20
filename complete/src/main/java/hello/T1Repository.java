package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface T1Repository extends CrudRepository<T1, Long> {
  @Query(value = "select x.id from hello.T1 x where x.a = ?1 and x.b = ?2 and x.c = ?3")
  UUID getT1IdByABC(UUID a, UUID b, UUID c);
}
