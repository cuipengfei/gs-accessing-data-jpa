package hello;

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
}
