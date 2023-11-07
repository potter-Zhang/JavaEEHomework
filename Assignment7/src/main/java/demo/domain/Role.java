package demo.domain;

import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role {
    @Id
    String id;
    String remark;

    @Convert(converter = StringListConverter.class)
    List<String> authorities = new ArrayList<>();
}
