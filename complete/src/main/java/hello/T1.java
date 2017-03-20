package hello;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t1", schema = "test")
public class T1 extends BaseEntity {

  @Column(nullable = false)
  private UUID someField;

  @Column()
  private ZonedDateTime date;

  public T1(UUID someField, ZonedDateTime date) {
    this.someField = someField;
    this.date = date;
  }
}
