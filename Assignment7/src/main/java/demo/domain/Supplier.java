package demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Supplier {
    @Id
    String id;
    String name;
}
