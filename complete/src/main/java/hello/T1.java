package hello;

import static javax.persistence.CascadeType.ALL;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

import org.hibernate.annotations.LazyCollection;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t1", schema = "test")
public class T1 extends BaseEntity {

  @Column(nullable = false)
  private UUID a;
  @Column(nullable = false)
  private UUID b;
  @Column(nullable = false)
  private UUID c;

  @LazyCollection(FALSE)
  @OneToMany(cascade = ALL, mappedBy = "t1")
  private List<T2> lineItems;

}
