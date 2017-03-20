package hello;

import static javax.persistence.CascadeType.ALL;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

import org.hibernate.annotations.LazyCollection;

import java.util.ArrayList;
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

  @LazyCollection(FALSE)
  @OneToMany(cascade = ALL, mappedBy = "t1")
  private List<T2> t2s = new ArrayList<>();

  public List<T2> getT2s() {
    return t2s;
  }

  public T1(UUID a) {
    this.a = a;
  }
}
