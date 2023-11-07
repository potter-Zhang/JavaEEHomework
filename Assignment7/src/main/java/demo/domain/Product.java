package demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    String id;
    String name;
    float price;

    @OneToOne(fetch = FetchType.EAGER)
    Supplier supplier;


}
