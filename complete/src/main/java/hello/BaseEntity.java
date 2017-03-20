
package hello;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

  public static final String PG_UUID = "pg-uuid";

  @Id
  @GeneratedValue(generator = "uuid-gen")
  @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
  @Type(type = PG_UUID)
  protected UUID id;

  public void setId(UUID id) {
    this.id = id;
  }

  public BaseEntity() {
  }
}
