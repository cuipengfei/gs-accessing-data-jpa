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
  public void insertMany() {
    for (int i = 0; i < 10; i++) {
      log.info("!!! " + (i + 1) + "th item start");

      UUID a = UUID.randomUUID();

      T1 t1 = new T1(a);

      UUID t1Id = tryToFindExistingT1(a);

      if (t1Id == null) {
        log.info("t1 not found");

        T1 savedT1 = saveT1(t1);
        saveT2(savedT1);
      }

      log.info("!!! " + (i + 1) + "th item finished");
      log.info("====================================");
    }

  }

  private UUID tryToFindExistingT1(UUID a) {
    long start = currentTimeMillis();
    log.info("start find");
    UUID t1Id = t1Repository.getT1IdByABC(a);
    //the line above will become very very very slow, but if you remove date on t2, it'll be faster
    log.info("find finished: " + (currentTimeMillis() - start));
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
    //remove the line above, then there won't be update statements
    //and find t1 will be much faster

    savedT1.getT2s().add(t2);

    t2Repository.save(t2);
    log.info("save t2: " + (currentTimeMillis() - start));
  }
}
