package hello;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t2", schema = "test")
public class T2 extends BaseEntity {

  @ManyToOne()
  @JoinColumn(nullable = false)
  private T1 t1;

  @Column()
  private ZonedDateTime date;

  public T2(T1 t1) {
    this.t1 = t1;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }
}
