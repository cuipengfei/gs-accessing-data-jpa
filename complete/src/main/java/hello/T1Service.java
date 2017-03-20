package hello;

import static java.lang.System.currentTimeMillis;
import static java.time.ZonedDateTime.now;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class T1Service {
  private static final Logger log = LoggerFactory.getLogger(T1Service.class);

  @Autowired
  T1Repository t1Repository;

  @Transactional
  public void insertMany() {
    for (int i = 0; i < 1000; i++) {
      log.info("!!! " + (i + 1) + "th item start");

      UUID randomUUID = UUID.randomUUID();
      T1 foundT1 = tryToFindExistingT1(randomUUID);//certainly won't find

      if (foundT1 == null) {
        log.info("t1 not found");
        T1 t1 = new T1(randomUUID, now());
        saveT1(t1);
      }

      log.info("!!! " + (i + 1) + "th item finished");
      log.info("====================================");
    }
  }

  private T1 tryToFindExistingT1(UUID someField) {
    long start = currentTimeMillis();
    T1 t1Id = t1Repository.findBySomeField(someField);
    //as nth item increases, the line above will become very very slow
    log.info("find took: " + (currentTimeMillis() - start) + " milliseconds");
    return t1Id;
  }

  private T1 saveT1(T1 t1) {
    long start = currentTimeMillis();
    T1 savedT1 = t1Repository.save(t1);
    log.info("save took: " + (currentTimeMillis() - start) + " milliseconds");
    return savedT1;
  }

}
