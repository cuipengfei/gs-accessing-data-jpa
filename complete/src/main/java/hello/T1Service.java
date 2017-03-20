package hello;

import static java.lang.System.currentTimeMillis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class T1Service {
  private static final Logger log = LoggerFactory.getLogger(T1Service.class);

  @Autowired
  T1Repository t1Repository;

  @Autowired
  T2Repository t2Repository;

  @Transactional
  public void createManyT1s() {
    for (int i = 0; i < 1000; i++) {
      UUID a = UUID.randomUUID();
      UUID b = UUID.randomUUID();
      UUID c = UUID.randomUUID();

      T1 t1 = new T1(a, b, c);

      UUID t1Id = tryToFindExistingT1(a, b, c);

      if (t1Id == null) {
        log.info("t1 not found");

        T1 savedT1 = saveT1(t1);
        saveT2(savedT1);
      }
    }

  }

  private UUID tryToFindExistingT1(UUID a, UUID b, UUID c) {
    long start = currentTimeMillis();
    UUID t1Id = t1Repository.getT1IdByABC(a, b, c);
    log.info("try to find t1: " + (currentTimeMillis() - start));
    return t1Id;
  }

  private T1 saveT1(T1 t1) {
    long start = currentTimeMillis();
    T1 savedT1 = t1Repository.save(t1);
    log.info("save t1: " + (currentTimeMillis() - start));
    return savedT1;
  }

  private void saveT2(T1 savedT1) {
    long start = currentTimeMillis();

    T2 t2 = new T2(savedT1);
    t2.setDate(ZonedDateTime.now());
    //remove the line above, find t1 will be faster and there won't be update statements

    savedT1.getT2s().add(t2);

    t2Repository.save(t2);
    log.info("save t2: " + (currentTimeMillis() - start));
  }
}
